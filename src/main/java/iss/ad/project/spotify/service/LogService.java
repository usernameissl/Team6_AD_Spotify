package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.*;
import iss.ad.project.spotify.repo.LogRepo;
import iss.ad.project.spotify.repo.SpotifyNameRepo;
import iss.ad.project.spotify.repo.SpotifyRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    private LogRepo logRepo;

    @Autowired
    private SpotifyRepo spotifyRepo;

    @Autowired
    private SpotifyNameRepo sNameRepo;

    public LogEntry save(LogEntry logEntry){
        return logRepo.save(logEntry);
    }

    public List<String> getDistinctNames() {
        return logRepo.findDistinctNames();
    }
    // check if length is exactly 1 easier than using Optional?
    public List<LogEntry> getUniqueLogEntry(String name, int taskId, int modelId) {
        return logRepo.findByNameAndTaskIdAndModelId(name, taskId, modelId);
    }

    public List<Integer> getDistinctByTaskId(){
        return logRepo.findDistinctTaskId();
    }

    public List<Integer> getDistinctByModelId(){
        return logRepo.findDistinctModelId();
    }

    public List<LogEntry> getAll(){
        return logRepo.findAll();
    }

    public Map<String, Map<String, Integer>> fetchBacktracksPerUserForAllModelsTasks() {
        Map<String, Map<String, Integer>> modelTaskBacktracks = new HashMap<>();

        for (int modelId = 1; modelId <= 2; modelId++) {
            for (int taskId = 1; taskId <= 3; taskId++) {
                List<LogEntry> entries = logRepo.findByModelIdAndTaskId(modelId, taskId);

                // Sort entries by name and orderValue
                entries.sort(Comparator.comparing(LogEntry::getName).thenComparing(LogEntry::getOrderValue));

                Map<String, Integer> backtracks = new HashMap<>();
                String previousName = null;
                int previousLayer = 0;

                for (LogEntry entry : entries) {
                    backtracks.putIfAbsent(entry.getName(), 0); // Each user entry in ModelTaskPair will have a default value of 0

                    if (!entry.getName().equals(previousName)) {
                        previousLayer = entry.getLayer();
                        previousName = entry.getName();
                    } else {
                        if (previousLayer == 2 && entry.getLayer() == 1 || previousLayer == 3 && entry.getLayer() == 2 || previousLayer == entry.getLayer()) {
                            backtracks.put(entry.getName(), backtracks.get(entry.getName()) + 1);
                        }
                        previousLayer = entry.getLayer();
                    }
                }

                String key = "model" + modelId + "task" + taskId;
                modelTaskBacktracks.put(key, backtracks);
            }
        }
        return modelTaskBacktracks;
    }

    public Map<String, Double> computeModelAverages(Map<String, Map<String, Integer>> modelTaskBacktracks) {
        Map<String, Double> modelAverages = new HashMap<>();

        for (int modelId = 1; modelId <= 2; modelId++) {
            Map<String, Integer> modelTotalBacktracks = new HashMap<>();
            Map<String, Integer> modelTaskCount = new HashMap<>();

            for (int taskId = 1; taskId <= 3; taskId++) {
                String key = "model" + modelId + "task" + taskId;

                modelTaskBacktracks.get(key).forEach((user, backtracks) -> {
                    modelTotalBacktracks.put(user, modelTotalBacktracks.getOrDefault(user, 0) + backtracks);
                    modelTaskCount.put(user, modelTaskCount.getOrDefault(user, 0) + 1);
                });
            }

            for (String user : modelTotalBacktracks.keySet()) {
                double average = (double) modelTotalBacktracks.get(user) / modelTaskCount.get(user);
                modelAverages.put("model" + modelId + user, average);
            }
        }
        return modelAverages;
    }

    //Binary tree
    // public Node buildTreeForUser(String username) {
    //     List<LogEntry> entries = logRepo.findByName(username);
    //     return buildTree(entries, 0);
    // }

    // private Node buildTree(List<LogEntry> entries, int layer) {
    //     if (entries.isEmpty() || layer >= entries.size()) return null;

    //     Node node = new Node();
    //     LogEntry entry = entries.get(layer);
        
    //     node.setName(entry.getGenre() + " (" + entry.getOrderValue() + ")");
        
    //     node.setLeft(buildTree(entries, 2 * layer + 1)); // Left child
    //     node.setRight(buildTree(entries, 2 * layer + 2)); // Right child

    //     return node;
    // }

    // public Map<String, Object> convertTreeToMap(Node node) {
    //     Map<String, Object> map = new HashMap<>();
    //     if (node == null) return map;

    //     map.put("name", node.getName());
    //     if (node.getLeft() != null) map.put("left", convertTreeToMap(node.getLeft()));
    //     if (node.getRight() != null) map.put("right", convertTreeToMap(node.getRight()));

    //     return map;
    // }
    public SpotifyName buildSpotifyLayersTree() {
        List<SpotifyName> layers = sNameRepo.findAll();
    
        Map<Double, SpotifyName> layerMap = layers.stream()
            .collect(Collectors.toMap(SpotifyName::getLayerId, Function.identity()));
    
        SpotifyName root = new SpotifyName();
        root.setName("Start");
    
        for (SpotifyName layer : layers) {
            if (String.valueOf(layer.getLayerId()).endsWith(".0")) {
                root.getChildren().add(layer);
            } else {
                double parentLayerId = Double.parseDouble(String.valueOf(layer.getLayerId()).split("\\.")[0] + ".0");
                SpotifyName parent = layerMap.get(parentLayerId);
                if (parent != null) {
                    parent.getChildren().add(layer);
                }
            }
        }
        return root;
    }
    
    public SpotifyName buildTreeForUser(String name, int modelId, int taskId) {
        SpotifyName spotifyRoot = buildSpotifyLayersTree();
        List<LogEntry> userLogs = logRepo.findByNameAndTaskIdAndModelId(name, modelId, taskId);
        modifyTreeWithLogs(spotifyRoot, userLogs);
        return spotifyRoot;
    }
    
    private SpotifyName modifyTreeWithLogs(SpotifyName node, List<LogEntry> userLogs) {
        if (node == null) return null;

        List<LogEntry> relevantLogs = userLogs.stream()
            .filter(log -> log.getGenre().equals(node.getName()))
            .collect(Collectors.toList());
    
        if (!relevantLogs.isEmpty()) {
            int sumOrderValue = relevantLogs.stream().mapToInt(LogEntry::getOrderValue).sum();
            node.setName(node.getName() + " (" + sumOrderValue + ")");
        }

        List<SpotifyName> prunedChildren = node.getChildren().stream()
            .map(child -> modifyTreeWithLogs(child, userLogs))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    
        node.setChildren(prunedChildren);
    
        if (relevantLogs.isEmpty() && prunedChildren.isEmpty()) return null;
    
        return node;
    }
    
    public Map<String, Object> convertSpotifyNameToMap(SpotifyName spotifyNode) {
        Map<String, Object> map = new HashMap<>();
        if (spotifyNode == null) return map;
    
        map.put("name", spotifyNode.getName());
    
        List<Map<String, Object>> childrenMaps = new ArrayList<>();
        for (SpotifyName child : spotifyNode.getChildren()) {
            childrenMaps.add(convertSpotifyNameToMap(child));
        }
        if (!childrenMaps.isEmpty()) map.put("children", childrenMaps);
    
        return map;
    }
}

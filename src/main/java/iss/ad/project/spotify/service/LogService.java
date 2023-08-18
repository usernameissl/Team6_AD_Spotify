package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.*;
import iss.ad.project.spotify.repo.ClusterNameRepo;
import iss.ad.project.spotify.repo.LogRepo;
import iss.ad.project.spotify.repo.SpotifyNameRepo;
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
    private SpotifyNameRepo sNameRepo;

    @Autowired
    private ClusterNameRepo cNameRepo;

    public LogEntry save(LogEntry logEntry){
        return logRepo.save(logEntry);
    }

    public List<String> getDistinctNames() {
        return logRepo.findDistinctNames();
    }
    // check if length is exactly 1 easier than using Optional?
    public List<LogEntry> getUniqueLogEntry(String name, int modelId, int taskId) {
        return logRepo.findByNameAndModelIdAndTaskId(name, modelId, taskId);
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

    //Spotify model
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
        List<LogEntry> userLogs = getUniqueLogEntry(name, modelId, taskId);
        modifyTreeWithLogs(spotifyRoot, userLogs);
        return spotifyRoot;
    }
    
    private SpotifyName modifyTreeWithLogs(SpotifyName node, List<LogEntry> userLogs) {
        if (node == null) return null;

        List<LogEntry> relevantLogs = userLogs.stream()
            .filter(log -> log.getGenre().equals(node.getName()))
            .collect(Collectors.toList());
    
        if (!relevantLogs.isEmpty()) {
            String concatenatedOrderValues = relevantLogs.stream()
                .map(log -> Integer.toString(log.getOrderValue()))
                .collect(Collectors.joining(", ")); 
            node.setName(node.getName() + " (" + concatenatedOrderValues + ")");
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

    // For ClusterName
    public ClusterName buildClusterLayersTree() {
        List<ClusterName> layers = cNameRepo.findAll();

        Map<Double, ClusterName> layerMap = layers.stream()
            .collect(Collectors.toMap(ClusterName::getLayerId, Function.identity()));

        ClusterName root = new ClusterName();
        root.setName("Start");

        for (ClusterName layer : layers) {
            if (String.valueOf(layer.getLayerId()).endsWith(".0")) {
                root.getChildren().add(layer);
            } else {
                double parentLayerId = Double.parseDouble(String.valueOf(layer.getLayerId()).split("\\.")[0] + ".0");
                ClusterName parent = layerMap.get(parentLayerId);
                if (parent != null) {
                    parent.getChildren().add(layer);
                }
            }
        }
        return root;
    }

    public ClusterName buildClusterTreeForUser(String name, int modelId, int taskId) {
        ClusterName clusterRoot = buildClusterLayersTree();
        List<LogEntry> userLogs = getUniqueLogEntry(name, modelId, taskId);
        modifyTreeWithClusterLogs(clusterRoot, userLogs);
        return clusterRoot;
    }

    private ClusterName modifyTreeWithClusterLogs(ClusterName node, List<LogEntry> userLogs) {
        if (node == null) return null;
    
        List<LogEntry> relevantLogs = userLogs.stream()
            .filter(log -> log.getGenre().equals(node.getName()))
            .collect(Collectors.toList());
    
        if (!relevantLogs.isEmpty()) {
            String concatenatedOrderValues = relevantLogs.stream()
                .map(log -> Integer.toString(log.getOrderValue()))
                .collect(Collectors.joining(", "));
            node.setName(node.getName() + " (" + concatenatedOrderValues + ")");
        }
    
        List<ClusterName> prunedChildren = node.getChildren().stream()
            .map(child -> modifyTreeWithClusterLogs(child, userLogs))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    
        node.setChildren(prunedChildren);
    
        if (relevantLogs.isEmpty() && prunedChildren.isEmpty()) return null;
    
        return node;
    }
    
    public Map<String, Object> convertClusterNameToMap(ClusterName clusterNode) {
        Map<String, Object> map = new HashMap<>();
        if (clusterNode == null) return map;
    
        map.put("name", clusterNode.getName());
    
        List<Map<String, Object>> childrenMaps = new ArrayList<>();
        for (ClusterName child : clusterNode.getChildren()) {
            childrenMaps.add(convertClusterNameToMap(child));
        }
        if (!childrenMaps.isEmpty()) map.put("children", childrenMaps);
    
        return map;
    }

    public Integer findSuccessValueByCriteria(String name, int modelId, int taskId) {
        List<Integer> results = logRepo.findSuccessValueByCriteriaOrderedByOrderValueDesc(name, modelId, taskId);
        return results.isEmpty() ? null : results.get(0);
    }
}

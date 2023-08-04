package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.ClusterName;
import iss.ad.project.spotify.model.ClusterSong;
import iss.ad.project.spotify.repo.ClusterSongRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ClusterService {
    private final ClusterSongRepo clusterSongRepo;

    @Getter
    private List<String> layer1IdsCache;

    @Getter
    private List<String> layer2IdsCache;

    @Getter
    private Map<String, List<String>> layer1ToLayer2MapCache;

    @Getter
    private Map<String, List<ClusterSong>> layer2ToSongsMapCache;

    @Autowired
    public ClusterService(ClusterSongRepo clusterSongRepo) {
        this.clusterSongRepo = clusterSongRepo;
        refreshCache();
    }

    public void refreshCache() {
        layer1IdsCache = getDistinctLayer1();
        layer2IdsCache = getDistinctLayer2();
        layer1ToLayer2MapCache = getLayer1ToLayer2Mapping();
        layer2ToSongsMapCache = getLayer2ToSongsMapping();
    }

    public List<String> getDistinctLayer1() {
        return clusterSongRepo.findDistinctLayer1();
    }

    public List<String> getDistinctLayer2() {
        return clusterSongRepo.findDistinctLayer2();
    }

    public Map<String, List<String>>  getLayer1ToLayer2Mapping() {
        List<Object[]> pairs = clusterSongRepo.findLayer1AndLayer2Pairs();
        Map<String, Set<String>> setMap = new HashMap<>();
        for (Object[] pair : pairs) {
            String layer1Id = ((ClusterName) pair[0]).getName();
            String layer2Id = ((ClusterName) pair[1]).getName();
            setMap.computeIfAbsent(layer1Id, k -> new HashSet<>()).add(layer2Id);
        }
        Map<String, List<String>> resultMap = new HashMap<>();
        for(Map.Entry<String, Set<String>> entry : setMap.entrySet()){
            resultMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return resultMap;
    }

    public Map<String, List<ClusterSong>> getLayer2ToSongsMapping() {
        List<String> layer2Ids = getDistinctLayer2();
        Map<String, List<ClusterSong>> map = new HashMap<>();
        for (String layer2Id : layer2Ids) {
            List<ClusterSong> songs = clusterSongRepo.findSongsByLayer2(layer2Id);
            map.put(layer2Id, songs);
        }
        return map;
    }

}

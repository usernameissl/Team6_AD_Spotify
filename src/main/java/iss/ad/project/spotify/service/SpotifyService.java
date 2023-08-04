package iss.ad.project.spotify.service;
import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.repo.SpotifyRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpotifyService {

    private final SpotifyRepo spotifyRepo;

    @Getter
    private List<String> layer1IdsCache;

    @Getter
    private List<String> layer2IdsCache;
    @Getter
    private Map<String, List<String>> layer1ToLayer2MapCache;
    @Getter
    private Map<String, List<SpotifySong>> layer2ToSongsMapCache;

    @Autowired
    public SpotifyService(SpotifyRepo SpotifyRepo, SpotifyRepo spotifyRepo) {
        this.spotifyRepo = spotifyRepo;
        refreshCache();
    }

    public void refreshCache() {
        layer1IdsCache = getDistinctLayer1();
        layer2IdsCache = getDistinctLayer2();
        layer1ToLayer2MapCache = getLayer1ToLayer2Mapping();
        layer2ToSongsMapCache = getLayer2ToSongsMapping();
    }

    public List<String> getDistinctLayer1() {
        return spotifyRepo.findDistinctLayer1();
    }

    public List<String> getDistinctLayer2() {
        return spotifyRepo.findDistinctLayer2();
    }

    public Map<String, List<String>>  getLayer1ToLayer2Mapping() {
        List<Object[]> pairs = spotifyRepo.findLayer1AndLayer2Pairs();

        // Use set firs to get unique values
        Map<String, Set<String>> setMap = new HashMap<>();
        for (Object[] pair : pairs) {
            String layer1Id = (String) pair[0];
            String layer2Id = (String) pair[1];
            setMap.computeIfAbsent(layer1Id, k -> new HashSet<>()).add(layer2Id);
        }

        // Convert set to list
        Map<String, List<String>> resultMap = new HashMap<>();
        for(Map.Entry<String, Set<String>> entry : setMap.entrySet()){
            resultMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return resultMap;
    }

    public Map<String, List<SpotifySong>> getLayer2ToSongsMapping() {
        List<String> layer2Ids = spotifyRepo.findDistinctLayer2();
        Map<String, List<SpotifySong>> map = new HashMap<>();
        for (String layer2Id : layer2Ids) {
            List<SpotifySong> songs = spotifyRepo.findSongsByLayer2(layer2Id);
            map.put(layer2Id, songs);
        }
        return map;
    }

}

package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.ClusterName;
import iss.ad.project.spotify.model.ClusterSong;
import iss.ad.project.spotify.repo.ClusterSongRepo;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    // lastfm (search function not working well)
    public String getAlbumCoverUrl(String artist, String trackName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=fbf79e70392a0765afbe01a136fbb9af&artist=" + artist + "&track=" + trackName + "&format=json";
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonObj = new JSONObject(response);
        String imageUrl = "";
        if (jsonObj.has("track") && !jsonObj.isNull("track")) {
            JSONObject trackObj = jsonObj.getJSONObject("track");
            if (trackObj.has("album") && !trackObj.isNull("album")) {
                imageUrl = trackObj.getJSONObject("album").getJSONArray("image").getJSONObject(3).getString("#text");
            } else {
                imageUrl = "https://thisis-images.scdn.co/37i9dQZF1DZ06evO0jjjFK-default.jpg";
            }
        } else {
            imageUrl = "https://thisis-images.scdn.co/37i9dQZF1DZ06evO0jjjFK-default.jpg";
        }

        return imageUrl;
    }

}
package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.ClusterSong;
import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.ClusterService;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

// THIS IS NOT FOR USE AND IS PURELY FOR TESTING AND DISPLAY PURPOSES.
// If you need these return types please call the relevant service method.
@RestController
public class RestTestController {

    private final SpotifyService spotifyService;
    private final ClusterService clusterService;

    @Autowired
    public RestTestController(SpotifyService spotifyService, ClusterService clusterService) {
        this.spotifyService = spotifyService;
        this.clusterService = clusterService;
    }

    @GetMapping("spotify/layer1ids")
    public List<String> getSpotifyLayer1Ids() {
        return spotifyService.getLayer1IdsCache();
    }

    @GetMapping("spotify/layer2ids")
    public List<String> getSpotifyLayer2Ids() {
        return spotifyService.getLayer2IdsCache();
    }

    @GetMapping("spotify/layer1mapping")
    public Map<String, List<String>> getSpotifyLayer1ToLayer2Mapping() {
        Map<String, List<String>> map = spotifyService.getLayer1ToLayer2MapCache();
        map.forEach((key, value) -> System.out.println(key + ": " + value));
        return map;
    }

    @GetMapping("spotify/layer2mapping")
    public Map<String, List<SpotifySong>> getSpotifyLayer2ToSongsMapping() {
        Map<String, List<SpotifySong>> map = spotifyService.getLayer2ToSongsMapCache();
        map.forEach((key, value) -> {
            System.out.print(key + ": ");
            value.forEach(song -> System.out.print(song.getTrackName() + ", "));
            System.out.println();
        });
        return map;
    }


    @GetMapping("cluster/layer1ids")
    public List<String> getClusterLayer1Ids() {
        return clusterService.getLayer1IdsCache();
    }

    @GetMapping("cluster/layer2ids")
    public List<String> getClusterLayer2Ids() {
        return clusterService.getLayer2IdsCache();
    }

    @GetMapping("cluster/layer1mapping")
    public Map<String, List<String>> getClusterLayer1ToLayer2Mapping() {
        Map<String, List<String>> map = clusterService.getLayer1ToLayer2MapCache();
        map.forEach((key, value) -> System.out.println(key + ": " + value));
        return map;
    }

    @GetMapping("cluster/layer2mapping")
    public Map<String, List<ClusterSong>> getClusterLayer2ToSongsMapping() {
        Map<String, List<ClusterSong>> map = clusterService.getLayer2ToSongsMapCache();
        map.forEach((key, value) -> {
            System.out.print(key + ": ");
            value.forEach(song -> System.out.print(song.getTrackName() + ", "));
            System.out.println();
        });
        return map;
    }




}

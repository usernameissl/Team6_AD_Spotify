package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.SpotifySong;
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

    @Autowired
    public RestTestController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }


    @GetMapping("/layer1ids")
    public List<String> getLayer1Ids() {
        return spotifyService.getLayer1IdsCache();
    }

    @GetMapping("/layer2ids")
    public List<String> getLayer2Ids() {
        return spotifyService.getLayer2IdsCache();
    }

    @GetMapping("/layer1mapping")
    public Map<String, Set<String>> getLayer1ToLayer2Mapping() {
        Map<String, Set<String>> map = spotifyService.getLayer1ToLayer2MapCache();
        map.forEach((key, value) -> System.out.println(key + ": " + value));
        return map;
    }

    @GetMapping("/layer2mapping")
    public Map<String, List<SpotifySong>> getLayer2ToSongsMapping() {
        Map<String, List<SpotifySong>> map = spotifyService.getLayer2ToSongsMapCache();
        map.forEach((key, value) -> {
            System.out.print(key + ": ");
            value.forEach(song -> System.out.print(song.getTrackName() + ", "));
            System.out.println();
        });
        return map;
    }


}

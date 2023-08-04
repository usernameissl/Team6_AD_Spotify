package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class SongController {
    SpotifyService spotifyService;

    public SongController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    // test routing
    @GetMapping("/songs")
    public String getSongs() {
        return "songs";
    }
    @GetMapping("/{modelNo}/category/{layer1}/{layer2}")
    public String getPlaylistByLayer2(@PathVariable("modelNo") int modelNo,
                                      @PathVariable("layer1") String layer1,
                                      @PathVariable("layer2") String layer2,
                                      Model model) {
        if (modelNo == 1){
            Map<String, List<SpotifySong>> map = spotifyService.getLayer2ToSongsMapCache();
            List<SpotifySong> songs = map.get(layer2);
            // pass to view
            model.addAttribute("modelNo", modelNo);
            model.addAttribute("layer1", layer1);
            model.addAttribute("songs", songs);
        }
        // else if modelNo == 2 then get cluster genres instead
        return "songs";
    }
}

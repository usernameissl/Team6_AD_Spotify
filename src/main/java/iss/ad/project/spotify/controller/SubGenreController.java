package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.repo.SpotifyRepo;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.*;

@Controller
public class SubGenreController {

    private final SpotifyService spotifyService;

    @Autowired
    public SubGenreController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/category")
    public String getSubGenre() {
        return "sub-genre";
    }
    @GetMapping("/category/{modelNo}/{layer1}")
    public String getSubGenreByLayer1(@PathVariable("modelNo") int modelNo, @PathVariable("layer1") String layer1) {
        if (modelNo == 1){
            Map<String, List<String>> map = spotifyService.getLayer1ToLayer2MapCache();
            List<String> subgenres = map.get(layer1);
            // pass subgenres into the view
        }
        // else if modelNo == 2 then get cluster genres instead

        return "sub-genre";
    }

}

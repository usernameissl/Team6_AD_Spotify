package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.repo.SpotifyRepo;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private final SpotifyService spotifyService;

    @Autowired
    public CategoryController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/model1")
    public String getModel1() {
        return "model-1";
    }
}

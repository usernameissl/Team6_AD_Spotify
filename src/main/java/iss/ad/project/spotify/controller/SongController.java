package iss.ad.project.spotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {

    @GetMapping("/songs")
    public String getSongs() {
        return "songs";
    }
}

package iss.ad.project.spotify.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.SpotifyService;

@Controller
@RequestMapping(value = "/songs")
public class SongController {

    @Autowired
    private SpotifyService spotifyService;
    
    @GetMapping("/{layer1}/{layer2}")
    public String getSongsByLayers(@PathVariable String layer1, @PathVariable String layer2, Model model) {

        Map<String, List<SpotifySong>> layer2ToSongsMap = spotifyService.getLayer2ToSongsMapping();
        List<SpotifySong> songs = layer2ToSongsMap.get(layer2);

        model.addAttribute("songs", songs);

        return "songs";
    }



}

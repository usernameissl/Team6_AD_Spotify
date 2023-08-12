package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.ClusterSong;
import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.ClusterService;
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
    ClusterService clusterService;

    public SongController(SpotifyService spotifyService, ClusterService clusterService) {
        this.spotifyService = spotifyService;
        this.clusterService = clusterService;
    }

    // test routing
    @GetMapping("/songs")
    public String getSongs() {
        return "songs";
    }
    @GetMapping("/{modelNo}/{taskNo}/{layer1}/{layer2}/songs")
    public String getPlaylistByLayer2(@PathVariable("modelNo") int modelNo, @PathVariable("taskNo") int taskNo,
                                      @PathVariable("layer1") String layer1,
                                      @PathVariable("layer2") String layer2,
                                      Model model) {
        String task = getTask(taskNo);
        if (modelNo == 1){
            Map<String, List<SpotifySong>> map = spotifyService.getLayer2ToSongsMapCache();
            List<SpotifySong> songs = map.get(layer2);
            model.addAttribute("songs", songs);
        }
        else if(modelNo == 2){
            Map<String, List<ClusterSong>> map = clusterService.getLayer2ToSongsMapCache();
            List<ClusterSong> songs = map.get(layer2);
            model.addAttribute("songs", songs);
        }
        else {
            return "redirect:/home";
        }
        model.addAttribute("modelNo", modelNo);
        model.addAttribute("task",task);
        model.addAttribute("layer1", layer1);
        model.addAttribute("layer2", layer2);
        return "songs";
    }
    private String getTask(int taskId) {
        switch (taskId) {
            case 1:
                return "Task 1: Find a jazz love song by a male artist";
            case 2:
                return "Task 2: Find 'Call Me Maybe' by Carl Rae Jepsen";
            case 3:
                return "Task 3: Find a song by Irish rock band U2";
            default:
                return "Invalid Task!";
        }
    }
}

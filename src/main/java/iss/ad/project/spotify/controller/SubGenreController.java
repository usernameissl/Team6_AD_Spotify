package iss.ad.project.spotify.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import iss.ad.project.spotify.model.ClusterSong;
import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.ClusterService;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class SubGenreController {

    private final SpotifyService spotifyService;
    private final ClusterService clusterService;

    @Autowired
    public SubGenreController(SpotifyService spotifyService, ClusterService clusterService) {
        this.spotifyService = spotifyService;
        this.clusterService = clusterService;
    }

    // test routing
    @GetMapping("/category")
    public String getSubGenre() {
        return "sub-genre";
    }

    @GetMapping("/{modelNo}/{taskNo}/{layer1}")
    public String getSubGenreByLayer1(@PathVariable("modelNo") int modelNo, @PathVariable("taskNo") int taskNo,
                                      @PathVariable("layer1") String layer1,
                                      Model model) throws JsonProcessingException {
        String task = getTask(taskNo);
        Map<String, List<String>> map;
        List<String> subgenres;
        List<String> coverUrls = new ArrayList<>();
        if (modelNo == 1){
            map = spotifyService.getLayer1ToLayer2MapCache();
            subgenres = map.get(layer1);
            // get album cover url list
            for (String subgenre : subgenres) {
                Map<String, List<SpotifySong>> map2 = spotifyService.getLayer2ToSongsMapCache();
                List<SpotifySong> songs = map2.get(subgenre);
                Random rand = new Random();
                SpotifySong randSong = songs.get(rand.nextInt(songs.size()));
                String url = spotifyService.getAlbumCoverUrl(randSong.getSpotifyId());
                coverUrls.add(url);
            }
        }
        else if(modelNo == 2){
            map = clusterService.getLayer1ToLayer2MapCache();
            subgenres = map.get(layer1);
            // get album cover url list
            for (String subgenre : subgenres) {
                Map<String, List<ClusterSong>> map2 = clusterService.getLayer2ToSongsMapCache();
                List<ClusterSong> songs = map2.get(subgenre);
                Random rand = new Random();
                ClusterSong randSong = songs.get(rand.nextInt(songs.size()));
                String url = spotifyService.getAlbumCoverUrl(randSong.getSpotifyId());
                coverUrls.add(url);
            }
        }
        else {
            return "redirect:/home";
        }

        // pass subgenres into the view
        model.addAttribute("modelNo", modelNo);
        model.addAttribute("taskNo",taskNo);
        model.addAttribute("task",task);
        model.addAttribute("layer1", layer1);
        model.addAttribute("subgenres", subgenres);
        // pass covers to view
        model.addAttribute("coverUrls", coverUrls);
        return "sub-genre";
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

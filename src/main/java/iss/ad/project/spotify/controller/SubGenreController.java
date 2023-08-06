package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.repo.SpotifyRepo;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // test routing
    @GetMapping("/category")
    public String getSubGenre() {
        return "sub-genre";
    }
    @GetMapping("/{modelNo}/category/{layer1}")
    public String getSubGenreByLayer1(@PathVariable("modelNo") int modelNo,
                                      @PathVariable("layer1") String layer1,
                                      Model model) {
        if (modelNo == 1){
            Map<String, List<String>> map = spotifyService.getLayer1ToLayer2MapCache();
            List<String> subgenres = map.get(layer1);
            // pass subgenres into the view
            model.addAttribute("modelNo", modelNo);
            model.addAttribute("layer1", layer1);
            model.addAttribute("subgenres", subgenres);

            // get album cover url list
            List<String> coverUrls = new ArrayList<>();
            for (String subgenre : subgenres) {
                Map<String, List<SpotifySong>> map2 = spotifyService.getLayer2ToSongsMapCache();
                List<SpotifySong> songs = map2.get(subgenre);
                Random rand = new Random();
                SpotifySong randSong = songs.get(rand.nextInt(songs.size()));
                String url = spotifyService.getAlbumCoverUrl(randSong.getArtist(), randSong.getTrackName());
                coverUrls.add(url);
            }
            // pass covers to view
            model.addAttribute("coverUrls", coverUrls);
        }
        // else if modelNo == 2 then get cluster genres instead
        return "sub-genre";
    }

}

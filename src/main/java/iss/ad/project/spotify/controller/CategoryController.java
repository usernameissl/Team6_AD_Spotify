package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.User;
import iss.ad.project.spotify.repo.SpotifyRepo;
import iss.ad.project.spotify.service.SpotifyService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getModel1(@RequestParam("taskId") int taskId, User user, Model model) {

        Integer modelId = 1;
        String task = getTask(taskId);
        List<String> layerOneList = spotifyService.getDistinctLayer1();
        List<String> backgroundColors = backgroundColors(layerOneList);
        model.addAttribute("modelId",modelId);
        model.addAttribute("task",task);
        model.addAttribute("layerOneList",layerOneList);
        model.addAttribute("backgroundColors", backgroundColors);
        return "model-1";
    }

    private String getTask(int taskId) {
        switch (taskId) {
            case 1:
                return "Task 1: abc";
            case 2:
                return "Task 2: def";
            case 3:
                return "Task 3: gfh";
            default:
                return "Invalid Task!";
        }
    }
    private List<String> backgroundColors(List<String> layerOneList) {
        List<String> backgroundColors = new ArrayList<>();
        for (int i = 0; i < layerOneList.size(); i++) {
            String color;
            switch (i) {
                case 0:
                    color = "rgb(144, 28, 52)";
                    break;
                case 1:
                    color = "rgb(224, 68, 4)";
                    break;
                case 2:
                    color = "rgb(24, 140, 12)";
                    break;
                case 3:
                    color = "rgb(240, 20, 44)";
                    break;
                case 4:
                    color = "rgb(232, 28, 100)";
                    break;
                case 5:
                    color = "rgb(16, 116, 236)";
                    break;
                case 6:
                    color = "rgb(144, 100, 172)";
                    break;
                default:
                    color = "rgb(72, 124, 148)";
            }
            backgroundColors.add(color);
        }
        return backgroundColors;
    }

}
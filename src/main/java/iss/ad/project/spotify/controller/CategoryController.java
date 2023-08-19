package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.User;
import iss.ad.project.spotify.repo.SpotifyRepo;
import iss.ad.project.spotify.service.SpotifyService;
import iss.ad.project.spotify.service.ClusterService;


import java.util.List;
import java.util.ArrayList;

import iss.ad.project.spotify.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private final SpotifyService spotifyService;
    private final ClusterService clusterService;
    private final TaskService taskService;
    @Autowired
    public CategoryController(SpotifyService spotifyService, ClusterService clusterService, TaskService taskService) {
        this.spotifyService = spotifyService;
        this.clusterService = clusterService;
        this.taskService = taskService;
    }

    @GetMapping("/model")
    public String getModel1(@RequestParam("taskNo") int taskNo, @RequestParam("modelNo") int modelNo, User user, Model model) {

        List<String> tasks = taskService.getFormattedTasksCache();
        String task = tasks.get(taskNo-1); // get task ID

        List<String> layerOneList;
        if(modelNo == 1) { // model 1
            layerOneList = spotifyService.getDistinctLayer1();
        }
        else if(modelNo == 2){ // model 2
            layerOneList = clusterService.getDistinctLayer1();
        }
        else {
            return "redirect:/home";
        }
        List<String> backgroundColors = backgroundColors(layerOneList);
        model.addAttribute("modelNo",modelNo);
        model.addAttribute("taskNo",taskNo);
        model.addAttribute("task",task);
        model.addAttribute("layerOneList",layerOneList);
        model.addAttribute("backgroundColors", backgroundColors);
        return "model";
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
package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.User;
import iss.ad.project.spotify.repo.SpotifyRepo;
import iss.ad.project.spotify.service.ClusterService;
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
    private final ClusterService clusterService;

    @Autowired
    public CategoryController(SpotifyService spotifyService, ClusterService clusterService) {
        this.spotifyService = spotifyService;
        this.clusterService = clusterService;
    }

    @GetMapping("/model")
    public String getModel1(@RequestParam("taskId") int taskId, @RequestParam("modelId") int modelId, User user, Model model) {
    	
    	String task = getTask(taskId); // get task ID
    	List<String> layerOneList;
    	if(modelId == 1) { // model 1
    		layerOneList = spotifyService.getDistinctLayer1(); // sorted to ascending order
    	}
    	else if(modelId == 2){ // model 2
    		layerOneList = clusterService.getDistinctLayer1(); // sorted to ascending order
    	}
    	else {
    		return "redirect:/home";
    	}
    	List<String> backgroundColors = backgroundColors(layerOneList);
    	model.addAttribute("modelId",modelId);
    	model.addAttribute("task",task);
    	model.addAttribute("layerOneList",layerOneList);
    	model.addAttribute("backgroundColors", backgroundColors);
        return "model";
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
    	            color = "rgb(255, 0, 0)";
    	            break;
    	        case 1:
    	            color = "rgb(128, 128, 128)";
    	            break;
    	        case 2:
    	            color = "rgb(0, 0, 255)";
    	            break;
    	        case 3:
    	            color = "rgb(255, 165, 0)";
    	            break;
    	        case 4:
    	            color = "rgb(255, 105, 180)";
    	            break;
    	        case 5:
    	            color = "rgb(0, 255, 255)";
    	            break;
    	        case 6:
    	            color = "rgb(210, 105, 30)";
    	            break;
    	        default:
    	            color = "rgb(128, 0, 128)";
    	    }
    	    backgroundColors.add(color);
    	}
    	return backgroundColors;
    }
    
}

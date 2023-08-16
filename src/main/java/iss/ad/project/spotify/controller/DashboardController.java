package iss.ad.project.spotify.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import iss.ad.project.spotify.model.*;
import iss.ad.project.spotify.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value = "/admin")
@Controller
public class DashboardController {
    private final LogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public DashboardController(LogService logService){
        this.logService = logService;
    }

    @GetMapping("/backtracks")
    public String getBacktracks(Model model) throws JsonProcessingException {
        Map<String, Map<String, Integer>> backtracks = logService.fetchBacktracksPerUserForAllModelsTasks();
        Map<String, Double> averages = logService.computeModelAverages(backtracks);

        String jsonBacktracks = objectMapper.writeValueAsString(backtracks);
        String jsonAverages = objectMapper.writeValueAsString(averages);

        model.addAttribute("modelTaskBacktracksJson", jsonBacktracks);
        model.addAttribute("modelAveragesJson", jsonAverages);

        System.out.println(jsonBacktracks);
        //System.out.println(jsonAverages);

        return "dashboard";
    }

    @GetMapping("/logView")
    public String LogViewForm(Model model) {
        List<LogEntry> logs = logService.getAll();
        model.addAttribute("logs",logs );
        model.addAttribute("userList", logService.getDistinctNames());
        model.addAttribute("taskList", logService.getDistinctByTaskId());
        model.addAttribute("modelList",logService.getDistinctByModelId());
        return "logView";
    }

    @GetMapping("/genretimechart")
    public String getTimeChart(Model model) {
    	
    	// model 1 subGenres
        String[] model1SubGenres = {
        "Heavy Metal", "Metal Mix", "00s Metal Classics", "Metal Covers", "2023 Metal Core Playlist",
        "Classic Rock", "90 Rock Anthems", "All New Rocks", "Best of Rock 2000","Rock 2023 International",
        "Classic Punk", "Pink Punk", "Punk Goes Disney", "Punk Goes Pop 2023 Lates Hits", "Punk Unplugged",
        "Club Beats 2023", "EDM BANGERZ", "EDM Gaming", "EDM Workout", "Best EDM Songs of All The Times",
        "Hip Hop 90-20", "Hip Hop Classic", "Hip Hop Dance Playlist", "Hip Hop Hits", "Hip Hop Workout",
        "Jazz 2023", "Jazz Classic", "Jazz For Sleep", "Jazz In The Rain", "Lofi Jazz",
        "Dance Pop", "Pop Songs Everyone Knows", "Pop Hits 2023", "Pop Hits in 2000s- Throwback Vibes", "Pop Mix",
        "Old Country Songs", "Country Love Songs", "Country Popular 2023", "Classic Road Trip Songs", "Best Country Songs"
        };
        
        // model 2 subGenres
        String[] model2SubGenres = {
        "Throwback Tunes", "High Energy Hip Hop", "Pop and Punk", "High Energy Pop", "Rap",
        "Melodic Ambient Soundtracks", "Strings", "Energetic Instrumentals", "Soothing Instrumentals","Otherwordly Vibes",
        "Acoustic Pop", "Jazz and Blues", "Folk", "Country", "Soulful",
        "Club Beats", "Jazztronica", "Indietronica", "Organic Electronic", "Electropop",
        "World", "Trap", "Hip Hop", "Pop Rock", "Percussive Beats",
        "Reggae", "R&B", "Rock", "Dance Rock", "Alt and Indie Pop"
        };
    	
    	// retrieve all logs
    	List<LogEntry> logs = logService.getAll();
    	
    	// get all model 1 task 1 genre and sum their respective think time
        Map<String, Integer> model1Task1GenreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == 1 && log.getTaskId() == 1 && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        // sort according to model 1 task 1 subGenres order and convert from milliseconds to minutes
        Map<String, Double> sortedModel1Task1GenreThinkTime = new LinkedHashMap<>();
        for (String model1SubGenre : model1SubGenres) {
            if (model1Task1GenreThinkTime.containsKey(model1SubGenre)) {
            	sortedModel1Task1GenreThinkTime.put(model1SubGenre, model1Task1GenreThinkTime.get(model1SubGenre) / 60000.0);
            } else {
            	sortedModel1Task1GenreThinkTime.put(model1SubGenre, 0.0);
            }
        }
        
    	// get all model 1 task 2 genre and sum their respective think time
        Map<String, Integer> model1Task2GenreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == 1 && log.getTaskId() == 2 && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        // sort according to model 1 task 2 subGenres order and convert from milliseconds to minutes
        Map<String, Double> sortedModel1Task2GenreThinkTime = new LinkedHashMap<>();
        for (String model1SubGenre : model1SubGenres) {
            if (model1Task2GenreThinkTime.containsKey(model1SubGenre)) {
            	sortedModel1Task2GenreThinkTime.put(model1SubGenre, model1Task2GenreThinkTime.get(model1SubGenre) / 60000.0);
            } else {
            	sortedModel1Task2GenreThinkTime.put(model1SubGenre, 0.0);
            }
        }
        
    	// get all model 1 task 3 genre and sum their respective think time
        Map<String, Integer> model1Task3GenreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == 1 && log.getTaskId() == 3 && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        // sort according to model 1 task 3 subGenres order and convert from milliseconds to minutes
        Map<String, Double> sortedModel1Task3GenreThinkTime = new LinkedHashMap<>();
        for (String model1SubGenre : model1SubGenres) {
            if (model1Task3GenreThinkTime.containsKey(model1SubGenre)) {
            	sortedModel1Task3GenreThinkTime.put(model1SubGenre, model1Task3GenreThinkTime.get(model1SubGenre) / 60000.0);
            } else {
            	sortedModel1Task3GenreThinkTime.put(model1SubGenre, 0.0);
            }
        }
        
    	// get all model 2 task 1 genre and sum their respective think time
        Map<String, Integer> model2Task1GenreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == 2 && log.getTaskId() == 1 && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        // sort according to model 1 task 1 subGenres order and convert from milliseconds to minutes
        Map<String, Double> sortedModel2Task1GenreThinkTime = new LinkedHashMap<>();
        for (String model2SubGenre : model2SubGenres) {
            if (model2Task1GenreThinkTime.containsKey(model2SubGenre)) {
            	sortedModel2Task1GenreThinkTime.put(model2SubGenre, model2Task1GenreThinkTime.get(model2SubGenre) / 60000.0);
            } else {
            	sortedModel2Task1GenreThinkTime.put(model2SubGenre, 0.0);
            }
        }
        
    	// get all model 2 task 2 genre and sum their respective think time
        Map<String, Integer> model2Task2GenreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == 2 && log.getTaskId() == 2 && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        // sort according to model 1 task 2 subGenres order and convert from milliseconds to minutes
        Map<String, Double> sortedModel2Task2GenreThinkTime = new LinkedHashMap<>();
        for (String model2SubGenre : model2SubGenres) {
            if (model2Task2GenreThinkTime.containsKey(model2SubGenre)) {
            	sortedModel2Task2GenreThinkTime.put(model2SubGenre, model2Task2GenreThinkTime.get(model2SubGenre) / 60000.0);
            } else {
            	sortedModel2Task2GenreThinkTime.put(model2SubGenre, 0.0);
            }
        }
        
    	// get all model 2 task 3 genre and sum their respective think time
        Map<String, Integer> model2Task3GenreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == 2 && log.getTaskId() == 3 && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        // sort according to model 1 task 3 subGenres order and convert from milliseconds to minutes
        Map<String, Double> sortedModel2Task3GenreThinkTime = new LinkedHashMap<>();
        for (String model2SubGenre : model2SubGenres) {
            if (model2Task3GenreThinkTime.containsKey(model2SubGenre)) {
            	sortedModel2Task3GenreThinkTime.put(model2SubGenre, model2Task3GenreThinkTime.get(model2SubGenre) / 60000.0);
            } else {
            	sortedModel2Task3GenreThinkTime.put(model2SubGenre, 0.0);
            }
        }
        
        model.addAttribute("sortedModel1Task1GenreThinkTime",sortedModel1Task1GenreThinkTime);
        model.addAttribute("sortedModel1Task2GenreThinkTime",sortedModel1Task2GenreThinkTime);
        model.addAttribute("sortedModel1Task3GenreThinkTime",sortedModel1Task3GenreThinkTime);
        model.addAttribute("sortedModel2Task1GenreThinkTime",sortedModel2Task1GenreThinkTime);
        model.addAttribute("sortedModel2Task2GenreThinkTime",sortedModel2Task2GenreThinkTime);
        model.addAttribute("sortedModel2Task3GenreThinkTime",sortedModel2Task3GenreThinkTime);
        return "timechart";
    }

    @GetMapping("/userlogs/{username}")
    public String visualizeUserLogs(@PathVariable String username, Model model) {

        SpotifyName root = logService.buildTreeForUser(username);
    
        Map<String, Object> treeMap = logService.convertSpotifyNameToMap(root);

        model.addAttribute("treeData", new Gson().toJson(treeMap)); 
    
        return "userlogs";
    }
    
}

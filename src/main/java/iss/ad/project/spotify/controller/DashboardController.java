package iss.ad.project.spotify.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import iss.ad.project.spotify.model.*;
import iss.ad.project.spotify.service.LogService;
import iss.ad.project.spotify.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value = "/admin")
@Controller
public class DashboardController {
    private final LogService logService;
    private final TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public DashboardController(LogService logService, TaskService taskService) {
        this.logService = logService;
        this.taskService = taskService;
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

        return "backtracks";
    }

    @GetMapping("/logView")
    public String LogViewForm(Model model) {
        List<String> tasks = taskService.getFormattedTasksCache();
        model.addAttribute("tasks",tasks);
        List<LogEntry> logs = logService.getAll();
        model.addAttribute("logs", logs);
        model.addAttribute("userList", logService.getDistinctNames());
        model.addAttribute("taskList", logService.getDistinctByTaskId());
        model.addAttribute("modelList", logService.getDistinctByModelId());
        return "logView";
    }

    @GetMapping("/genretimechart")
    public String getTimeChart(Model model) {

        List<String> tasks = taskService.getFormattedTasksCache();
        model.addAttribute("tasks",tasks);

        // model 1 subGenres
        String[] model1SubGenres = {
                "Heavy Metal", "Metal Mix", "00s Metal Classics", "Metal Covers", "2023 Metal Core Playlist",
                "Classic Rock", "90 Rock Anthems", "All New Rocks", "Best of Rock 2000", "Rock 2023 International",
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
                "Melodic Ambient Soundtracks", "Strings", "Energetic Instrumentals", "Soothing Instrumentals", "Otherwordly Vibes",
                "Acoustic Pop", "Jazz and Blues", "Folk", "Country", "Soulful",
                "Club Beats", "Jazztronica", "Indietronica", "Organic Electronic", "Electropop",
                "World", "Trap", "Hip Hop", "Pop Rock", "Percussive Beats",
                "Reggae", "R&B", "Rock", "Dance Rock", "Alt and Indie Pop"
        };
        List<LogEntry> logs = logService.getAll();

        Map<String, Double> sortedModel1Task1GenreThinkTime = getSortedGenreThinkTime(logs, 1, 1, model1SubGenres);
        Map<String, Double> sortedModel1Task2GenreThinkTime = getSortedGenreThinkTime(logs, 1, 2, model1SubGenres);
        Map<String, Double> sortedModel1Task3GenreThinkTime = getSortedGenreThinkTime(logs, 1, 3, model1SubGenres);

        Map<String, Double> sortedModel2Task1GenreThinkTime = getSortedGenreThinkTime(logs, 2, 1, model2SubGenres);
        Map<String, Double> sortedModel2Task2GenreThinkTime = getSortedGenreThinkTime(logs, 2, 2, model2SubGenres);
        Map<String, Double> sortedModel2Task3GenreThinkTime = getSortedGenreThinkTime(logs, 2, 3, model2SubGenres);

        model.addAttribute("sortedModel1Task1GenreThinkTime", sortedModel1Task1GenreThinkTime);
        model.addAttribute("sortedModel1Task2GenreThinkTime", sortedModel1Task2GenreThinkTime);
        model.addAttribute("sortedModel1Task3GenreThinkTime", sortedModel1Task3GenreThinkTime);
        model.addAttribute("sortedModel2Task1GenreThinkTime", sortedModel2Task1GenreThinkTime);
        model.addAttribute("sortedModel2Task2GenreThinkTime", sortedModel2Task2GenreThinkTime);
        model.addAttribute("sortedModel2Task3GenreThinkTime", sortedModel2Task3GenreThinkTime);

        return "timechart";
    }

    // Helper function to get and sort genre think time
    private Map<String, Double> getSortedGenreThinkTime(List<LogEntry> logs, int modelId, int taskId, String[] subGenres) {
        Map<String, Integer> genreThinkTime = logs.stream()
                .filter(log -> log.getModelId() == modelId && log.getTaskId() == taskId && log.getLayer() == 2)
                .collect(Collectors.toMap(LogEntry::getGenre, LogEntry::getThinkTime, Integer::sum));

        Map<String, Double> sortedGenreThinkTime = new LinkedHashMap<>();
        for (String subGenre : subGenres) {
            sortedGenreThinkTime.put(subGenre, genreThinkTime.getOrDefault(subGenre, 0) / 1000.0);
        }

        return sortedGenreThinkTime;
    }

    @GetMapping("/userlogs")
    public String showForm(Model model) {
        List<String> tasks = taskService.getFormattedTasksCache();
        model.addAttribute("tasks",tasks);
        model.addAttribute("userList", logService.getDistinctNames());
        return "userlogs";
    }

    @PostMapping("/userlogs")
    public String visualizeUserLogs(@RequestParam String name, 
                                    @RequestParam int modelId,
                                    @RequestParam int taskId, 
                                    Model model) {
    
        if (modelId == 1) { 
            SpotifyName spotifyRoot = logService.buildTreeForUser(name, modelId, taskId);
            Map<String, Object> spotifyTreeMap = logService.convertSpotifyNameToMap(spotifyRoot);
            model.addAttribute("treeData", spotifyTreeMap);
        } else if (modelId == 2) { 
            ClusterName clusterRoot = logService.buildClusterTreeForUser(name, modelId, taskId);
            Map<String, Object> clusterTreeMap = logService.convertClusterNameToMap(clusterRoot);
            model.addAttribute("treeData", clusterTreeMap);
        } else {
            throw new IllegalArgumentException("Invalid modelId provided.");
        }
        List<String> tasks = taskService.getFormattedTasksCache();
        model.addAttribute("tasks",tasks);
        model.addAttribute("userList", logService.getDistinctNames());

        Integer successValue = logService.findSuccessValueByCriteria(name, modelId, taskId);
        model.addAttribute("success", successValue);

        String totalThinkTime = logService.getTotalThinkTimeFormat(name, modelId, taskId);
        model.addAttribute("totalThinkTime", totalThinkTime);

        model.addAttribute("selectedName", name);
        model.addAttribute("selectedModelId", modelId);
        model.addAttribute("selectedTaskId", taskId);
    
        return "userlogs"; 
    }
}



package iss.ad.project.spotify.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import iss.ad.project.spotify.model.LogEntry;
import iss.ad.project.spotify.model.SessionData;
import iss.ad.project.spotify.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@RestController
public class LogController {

    private LogService logService;
    private static final String logFilePath = "C:\\Logs\\";


    @Autowired
    public LogController(LogService logService){
        this.logService = logService;
    }
    @PostMapping("/save-session-data")
    public String saveSessionData(@RequestBody String jsonStr) {
        try {
            List<LogEntry> logEntries = convertJsonToLogEntry(jsonStr);
            for (LogEntry logEntry : logEntries) {
                logService.save(logEntry);

                // Print the name, modelId, and taskId after saving
                System.out.printf("Saved LogEntry: Name: %s, Model: %d, Task: %d, Order: %d%n ",
                        logEntry.getName(), logEntry.getModelId(), logEntry.getTaskId(), logEntry.getOrderValue());
            }

            return "Data saved successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while saving the data.";
        }
    }



    public List<LogEntry> convertJsonToLogEntry(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LogEntry> logEntries = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonStr);

            // Get all the top level properties
            String name = rootNode.get("name").asText();
            int age = rootNode.get("age").asInt();
            boolean gender = rootNode.get("gender").asText().equalsIgnoreCase("M");
            int modelId = rootNode.get("modelId").asInt();
            int taskId = rootNode.get("taskId").asInt();

            JsonNode successNode = rootNode.get("success");
            int finalSuccessValue = 0;
            if (successNode != null) {
                finalSuccessValue = successNode.asBoolean() ? 1 : 2;
            }

            // Get the history array and create a log entry for each history index
            JsonNode historyNode = rootNode.get("history");
            String previousGenre = null;
            if (historyNode.isArray()) {
                for (int i = 0; i < historyNode.size(); i++) {
                    JsonNode node = historyNode.get(i);
                    String[] parts = node.asText().split(", ");
                    if (parts.length == 3) {

                        // Skip if genre is the same to prevent logging multiple clicks on the same box
                        String currentGenre = parts[2];
                        if (currentGenre.equals(previousGenre)) {
                            continue;
                        }
                        LogEntry logEntry = new LogEntry();
                        logEntry.setName(name);
                        logEntry.setAge(age);
                        logEntry.setGender(gender);
                        logEntry.setModelId(modelId);
                        logEntry.setTaskId(taskId);
                        logEntry.setSuccessValue(finalSuccessValue);
                        logEntry.setOrderValue(i + 1);
                        logEntry.setThinkTime(Integer.parseInt(parts[0]));
                        logEntry.setLayer(Integer.parseInt(parts[1]));
                        logEntry.setGenre(parts[2]);


                        // Set successValue to 1 or 2 only for the last entry, else set to 0
                        if (i == historyNode.size() - 1) {
                            logEntry.setSuccessValue(finalSuccessValue);
                        } else {
                            logEntry.setSuccessValue(0);
                        }
                        logEntries.add(logEntry); // save the entry

                        previousGenre = currentGenre;

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logEntries;
    }


}
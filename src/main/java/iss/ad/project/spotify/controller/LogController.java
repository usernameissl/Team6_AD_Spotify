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

    private static final Path processedFolder = Paths.get(logFilePath, "processed");



    @Autowired
    public LogController(LogService logService){
        this.logService = logService;
    }
    @PostMapping("/save-session-data")
    public String saveSessionData(@RequestBody SessionData sessionData) {
        try {
            // Convert the data to JSON (or any other format you prefer)
            String json = new ObjectMapper().writeValueAsString(sessionData);

            String filename = sessionData.getName() + "_" + sessionData.getModelId() + "_" + sessionData.getTaskId() + ".json";

            try (FileWriter file = new FileWriter( logFilePath + filename)) {
                file.write(json);
            }

            return "Data saved successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while saving the data.";
        }
    }

    @PostMapping("/process-logs")
    public void processLogs() {
        try {
            Map<String, Integer> nameCountMap = new HashMap<>();
            Files.createDirectories(processedFolder);

            // Only files that end with json that are in the Logs folder, excluding subdirectories
            Stream<Path> paths = Files.list(Paths.get(logFilePath))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".json"));

            // For each file read it, return its list of LogEntries and save them.
            paths.forEach(path -> {
                try {
                    String content = new String(Files.readAllBytes(path));
                    List<LogEntry> logEntries = convertJsonToLogEntry(content, nameCountMap);
                    for (LogEntry logEntry : logEntries) {
                        System.out.println(logEntry);
                        logService.save(logEntry);
                    }

                    Files.move(path, processedFolder.resolve(path.getFileName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<LogEntry> convertJsonToLogEntry(String jsonStr, Map<String, Integer> nameCountMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LogEntry> logEntries = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonStr);

            // Get all the top level properties
            String originalName = rootNode.get("name").asText();
            String baseName = originalName.split("-")[0];
            String newName = getNewName(baseName, nameCountMap); // change the GUID into sequential values if duplicates exist

            int age = rootNode.get("age").asInt();
            boolean gender = rootNode.get("gender").asText().equalsIgnoreCase("M");
            int modelId = rootNode.get("modelId").asInt();
            int taskId = rootNode.get("taskId").asInt();

            // Check if success value is present and assign value accordingly
            // 1 for success and 2 for fail
            JsonNode successNode = rootNode.get("success");
            int successValue = 0;
            if (successNode != null) {
                successValue = successNode.asBoolean() ? 1 : 2;
            }

            // Get the history array and create a log entry for each history index
            JsonNode historyNode = rootNode.get("history");
            if (historyNode.isArray()) {
                for (int i = 0; i < historyNode.size(); i++) {
                    JsonNode node = historyNode.get(i);
                    String[] parts = node.asText().split(", ");
                    if (parts.length == 3) {
                        LogEntry logEntry = new LogEntry();
                        logEntry.setName(newName);
                        logEntry.setAge(age);
                        logEntry.setGender(gender);
                        logEntry.setModelId(modelId);
                        logEntry.setTaskId(taskId);
                        logEntry.setSuccessValue(successValue);
                        logEntry.setOrderValue(i + 1); // +1 because order is 1-indexed
                        logEntry.setThinkTime(Integer.parseInt(parts[0]));
                        logEntry.setLayer(Integer.parseInt(parts[1]));
                        logEntry.setGenre(parts[2]);
                        logEntries.add(logEntry);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logEntries;
    }

    private String getNewName(String baseName, Map<String, Integer> nameCountMap) {
        int count = nameCountMap.getOrDefault(baseName, 0) + 1; // Increment count for this base name
        nameCountMap.put(baseName, count);
        return baseName + "-" + count;
    }

}
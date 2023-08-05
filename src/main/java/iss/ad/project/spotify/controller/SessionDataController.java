package iss.ad.project.spotify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import iss.ad.project.spotify.model.SessionData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class SessionDataController {

    private static final String filePath = "C:\\Logs\\";
    @PostMapping("/save-session-data")
    public String saveSessionData(@RequestBody SessionData sessionData) {
        try {
            // Convert the data to JSON (or any other format you prefer)
            String json = new ObjectMapper().writeValueAsString(sessionData);

            String filename = sessionData.getName() + "_" + sessionData.getModelId() + "_" + sessionData.getTaskId() + ".json";

            try (FileWriter file = new FileWriter( filePath + filename)) {
                file.write(json);
            }

            return "Data saved successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while saving the data.";
        }
    }
}
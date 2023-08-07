package iss.ad.project.spotify.model;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class SessionData {
    public String name;
    public int age;
    public String gender;
    public String modelId;
    public String taskId;
    public List<String> history;
    public boolean success;
}


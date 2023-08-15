package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.Task;
import iss.ad.project.spotify.repo.TaskRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    @Getter
    private List<String> formattedTasksCache;

    @Autowired
    public TaskService(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
        refreshCache();
    }

    public void refreshCache(){
        formattedTasksCache = getAllTasksFormatted();
    }

    public List<String> getAllTasksFormatted(){
        List<Task> allTasks = taskRepo.findAll();
        List<String> formattedTasks = new ArrayList<>();
        for (Task task: allTasks){
            String formatTask = "Task " + task.getId() + ": " + task.getName();
            formattedTasks.add(formatTask);
        }
        return formattedTasks;
    }
}
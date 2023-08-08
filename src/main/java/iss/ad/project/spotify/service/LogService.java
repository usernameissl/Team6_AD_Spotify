package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.LogEntry;
import iss.ad.project.spotify.repo.LogRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepo logRepo;
    @Autowired
    public LogService(LogRepo logRepo){
        this.logRepo = logRepo;
    }

    public LogEntry save(LogEntry logEntry){
        return logRepo.save(logEntry);
    }
}

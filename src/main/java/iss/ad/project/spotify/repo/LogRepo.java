package iss.ad.project.spotify.repo;

import iss.ad.project.spotify.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LogRepo extends JpaRepository<LogEntry, Long> {
    @Query("SELECT DISTINCT le.name FROM LogEntry le")
    List<String> findDistinctNames();
}

package iss.ad.project.spotify.repo;

import iss.ad.project.spotify.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogRepo extends JpaRepository<LogEntry, Long> {
}

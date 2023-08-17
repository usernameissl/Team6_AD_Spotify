package iss.ad.project.spotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import iss.ad.project.spotify.model.ClusterName;

public interface ClusterNameRepo extends JpaRepository<ClusterName, Long> {
    
}

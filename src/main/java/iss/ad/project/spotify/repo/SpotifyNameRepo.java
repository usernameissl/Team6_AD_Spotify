package iss.ad.project.spotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import iss.ad.project.spotify.model.SpotifyName;

public interface SpotifyNameRepo extends JpaRepository<SpotifyName, Long> {
    
}

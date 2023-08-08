package iss.ad.project.spotify.repo;

import iss.ad.project.spotify.model.SpotifySong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpotifyRepo extends JpaRepository<SpotifySong, Long> {
    @Query("SELECT DISTINCT s.layer1 FROM SpotifySong s ORDER BY s.layer1")
    List<String> findDistinctLayer1();

    @Query("SELECT DISTINCT s.layer2 FROM SpotifySong s")
    List<String> findDistinctLayer2();

    @Query("SELECT s.layer1, s.layer2 FROM SpotifySong s")
    List<Object[]> findLayer1AndLayer2Pairs();

    @Query("SELECT s FROM SpotifySong s WHERE s.layer2 = ?1")
    List<SpotifySong> findSongsByLayer2(String layer2Id);
}

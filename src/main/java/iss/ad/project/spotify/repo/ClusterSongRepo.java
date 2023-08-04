package iss.ad.project.spotify.repo;

import iss.ad.project.spotify.model.ClusterSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClusterSongRepo extends JpaRepository<ClusterSong, Long> {
    @Query("SELECT c.layer1, c.layer2 FROM ClusterSong c")
    List<Object[]> findLayer1AndLayer2Pairs();

    @Query("SELECT DISTINCT c.layer1.name FROM ClusterSong c")
    List<String> findDistinctLayer1();

    @Query("SELECT DISTINCT c.layer2.name FROM ClusterSong c")
    List<String> findDistinctLayer2();

    @Query("SELECT c FROM ClusterSong c WHERE c.layer2.name = :layer2Id")
    List<ClusterSong> findSongsByLayer2(@Param("layer2Id") String layer2Id);
}

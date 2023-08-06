package iss.ad.project.spotify.model;
import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="spotify_songs")
public class SpotifySong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Spotify_id")
    private String spotifyId;

    @Column(name = "Track_Name")
    private String trackName;

    private String artist;

    private String layer1;

    private String layer2;
}

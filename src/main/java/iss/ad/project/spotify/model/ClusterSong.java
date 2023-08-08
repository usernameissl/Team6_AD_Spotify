package iss.ad.project.spotify.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="cluster_songs")
public class ClusterSong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spotify_id")
    private String spotifyId;

    private String trackName;

    @OneToOne
    @JoinColumn(name="layer1_id", referencedColumnName="layer_id")
    private ClusterName layer1;

    @OneToOne
    @JoinColumn(name="layer2_id", referencedColumnName="layer_id")
    private ClusterName layer2;

    private String artist;
}
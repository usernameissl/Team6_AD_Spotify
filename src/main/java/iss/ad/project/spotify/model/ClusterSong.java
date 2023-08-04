package iss.ad.project.spotify.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    private String trackName;

    private int layer1_Id;

    private int layer2_Id;

    private String artist;
}

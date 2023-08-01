package iss.ad.project.spotify.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private int age;

    private Boolean gender;

    private int taskId;

    private int model;

    private int layer;

    private String genre;

    private int thinkTime;

    private int order;

    private int success;

    
}

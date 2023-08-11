package iss.ad.project.spotify.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="feedback")
public class Feedback implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "ENUM('UNHAPPY', 'NEUTRAL', 'SATISFIED')")
    @Enumerated(EnumType.STRING)
    private ExperienceEnum experience;
	
	private String comments;
	
	private String userName;
	private int age;
	
	

}

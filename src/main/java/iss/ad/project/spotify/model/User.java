package iss.ad.project.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private int age;

    private String gender;

    private int modelId;

    private int taskId;


}

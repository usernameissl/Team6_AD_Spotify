package iss.ad.project.spotify.model;

import java.util.*;

import lombok.Data;

@Data
public class Node {
    private String name;
    private List<Node> children = new ArrayList<>();
    
    public Node(String name) {
        this.name = name;
    }

    public Node(){}

}


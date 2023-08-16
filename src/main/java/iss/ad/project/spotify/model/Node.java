package iss.ad.project.spotify.model;

import lombok.Data;

@Data
public class Node {
    private String name;
    private Node left;
    private Node right;
    
    public Node(String name) {
        this.name = name;
    }

    public Node(){}

}


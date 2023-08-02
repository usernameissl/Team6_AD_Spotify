package iss.ad.project.spotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/category")
    public String getSubGenre() {
        return "sub-genre";
    }
}

package iss.ad.project.spotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/")
	public RedirectView redirectToHomePage() {
		return new RedirectView("/home");
	}
    @GetMapping("/home")
	public String getHomePage() {
		return "home";
	}

}

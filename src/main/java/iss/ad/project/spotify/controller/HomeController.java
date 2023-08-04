package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import iss.ad.project.spotify.model.User;

import java.util.*;

@Controller
public class HomeController {

	private final SpotifyService spotifyService;

	@Autowired
	public HomeController(SpotifyService spotifyService) {
		this.spotifyService = spotifyService;
	}

    @GetMapping("/")
	public RedirectView redirectToHomePage() {
		return new RedirectView("/home");
	}
    @GetMapping("/home")
	public String getHomePage() {
		return "home";
	}

	@PostMapping("/home")
	public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

		int modelId = user.getModelId();
		int taskId = user.getTaskId();
		redirectAttributes.addAttribute("taskId", taskId);
		
		if (modelId == 1) {
			return "redirect:/model1";
		} else if (modelId == 2) {
			return "redirect:/model2";
		} else {
			return "redirect:/home";
		}
	}

	//User click submit logfile will clear the current session of the user
	@GetMapping("/submitLogfile")
    public String submitLogfile() {
        return "redirect:/home";

		//...
	}

	// Not accessible to user, only used to refresh the cache
	@PostMapping("/refreshcache")
	public void refreshCache() {
		spotifyService.refreshCache();
	}


}

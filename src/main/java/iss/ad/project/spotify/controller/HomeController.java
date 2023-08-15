package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.service.ClusterService;
import iss.ad.project.spotify.service.SpotifyService;
import iss.ad.project.spotify.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private final ClusterService clusterService;
	private final TaskService taskService;

	@Autowired
	public HomeController(SpotifyService spotifyService, ClusterService clusterService, TaskService taskService) {
		this.spotifyService = spotifyService;
		this.clusterService = clusterService;
		this.taskService = taskService;
	}

	@GetMapping("/")
	public RedirectView redirectToHomePage() {
		return new RedirectView("/home");
	}
	@GetMapping("/home")
	public String getHomePage(Model model) {
		List<String> tasks = taskService.getFormattedTasksCache();
		model.addAttribute("tasks",tasks);
		return "home";
	}

	@PostMapping("/home")
	public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

		int modelNo = user.getModelId();
		int taskNo = user.getTaskId();
		redirectAttributes.addAttribute("taskNo", taskNo);
		redirectAttributes.addAttribute("modelNo", modelNo);

		if (modelNo != 0) {
			return "redirect:/model";
		}
		else {
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
		clusterService.refreshCache();
	}

}
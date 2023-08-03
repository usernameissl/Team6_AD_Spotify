package iss.ad.project.spotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import iss.ad.project.spotify.model.User;

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

	@PostMapping("/home")
	public String saveUser(@RequestParam String name,
						@RequestParam int age,
						@RequestParam String gender,
						@RequestParam int modelId,
						@RequestParam int taskId) {

		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setGender(gender);
		user.setModelId(modelId);
		user.setTaskId(taskId);

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
	

}

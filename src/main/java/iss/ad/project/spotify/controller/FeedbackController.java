package iss.ad.project.spotify.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ad.project.spotify.model.ExperienceEnum;
import iss.ad.project.spotify.model.Feedback;
import iss.ad.project.spotify.model.User;
import iss.ad.project.spotify.service.FeedbackService;

@Controller
public class FeedbackController {
	
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    
	@GetMapping("/feedback")
	public String getFeedback(Model model) {
		model.addAttribute("feedback", new Feedback());
		return "feedbackForm";
	}
	
	@PostMapping("/feedback")
		public String createFeedback(@ModelAttribute("feedback") Feedback feedback,
				@RequestParam("experience") String experience ,Model model){	
		Feedback userFeedback = new Feedback();
		ExperienceEnum experienceEnum = ExperienceEnum.valueOf(experience);
		userFeedback.setExperience(experienceEnum);
		userFeedback.setAge(feedback.getAge());
		userFeedback.setUserName(feedback.getUserName());
		feedbackService.create(userFeedback);		
		return "successMessage";
		}
	}



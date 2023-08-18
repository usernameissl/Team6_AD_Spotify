package iss.ad.project.spotify.controller;

import iss.ad.project.spotify.model.Feedback;
import iss.ad.project.spotify.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/feedback")
	public String getFeedbackPage(){
		return "feedback";
	}
	@PostMapping("/feedback")
	public String submitFeedback(@RequestParam int rating, @RequestParam String comments) {
		Feedback feedback = new Feedback();
		feedback.setRating(rating);
		feedback.setComments(comments);
		feedbackService.saveFeedback(feedback);
		return "redirect:/feedback";
	}
}


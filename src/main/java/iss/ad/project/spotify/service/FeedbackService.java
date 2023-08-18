package iss.ad.project.spotify.service;

import iss.ad.project.spotify.model.Feedback;
import iss.ad.project.spotify.repo.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepo feedbackRepository;
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}
}


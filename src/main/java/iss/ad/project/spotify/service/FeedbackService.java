package iss.ad.project.spotify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import iss.ad.project.spotify.model.Feedback;
import iss.ad.project.spotify.repo.FeedbackRepo;

@Service
public class FeedbackService {
	private FeedbackRepo feedbackRepo;
	
	@Autowired
	public FeedbackService(FeedbackRepo feedbackRepo) {
		this.feedbackRepo = feedbackRepo;
	}
	
	public List<Feedback>findFeedbackByExperienceStatus(@Param("ex") String ex){
		return feedbackRepo.findByExperience(ex);
	}
	public void create(Feedback feedback) {
		feedbackRepo.save(feedback);
	}

}

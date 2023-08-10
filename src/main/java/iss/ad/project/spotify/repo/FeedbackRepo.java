package iss.ad.project.spotify.repo;

import iss.ad.project.spotify.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
}


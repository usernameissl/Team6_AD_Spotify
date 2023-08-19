package iss.ad.project.spotify.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iss.ad.project.spotify.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

//	@Query("SELECT f FROM Feedback f WHERE f.experience = :experience")
//    List<Feedback> findByExperience(@Param("experience") String experience);
}



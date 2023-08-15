package iss.ad.project.spotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.ad.project.spotify.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

}
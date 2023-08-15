package iss.ad.project.spotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.ad.project.spotify.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {

	public Admin findByUsername(String username);
}
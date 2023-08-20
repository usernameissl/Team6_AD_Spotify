package iss.ad.project.spotify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ad.project.spotify.model.Admin;
import iss.ad.project.spotify.model.Task;
import iss.ad.project.spotify.repo.AdminRepo;
import iss.ad.project.spotify.repo.TaskRepo;

@Service
public class AdminService {
	
	private TaskRepo taskRepo;
	private AdminRepo adminRepo;
	
	@Autowired
	public AdminService(TaskRepo taskRepo, AdminRepo adminRepo) {
		this.taskRepo = taskRepo;
		this.adminRepo = adminRepo;
	}
	public void create(Task task) {
		taskRepo.save(task);
		
	}
	public void delete(Long id) {
		taskRepo.deleteById(id);
	}
	public Task update(Task task) {
		return taskRepo.save(task);
	}
	public List<Task> getAllTasks(){
		return taskRepo.findAll();	
	}
	public Optional<Task> findbyId(Long id) {
		return taskRepo.findById(id);
	}
	public Task findByTaskName(String name) {
		return taskRepo.findByName(name);
	}

	public Admin findByUsername(String username) {
		return adminRepo.findByUsername(username);
	}

    public Task getTaskById(long id) {
		return taskRepo.findById(id).orElse(null);
	}
}
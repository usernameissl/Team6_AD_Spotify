package iss.ad.project.spotify.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import iss.ad.project.spotify.model.Admin;
import iss.ad.project.spotify.model.Task;
import iss.ad.project.spotify.service.AdminService;

@Controller
public class AdminController {
	private final AdminService adminSrv;
	
	@Autowired
	public AdminController(AdminService adminSrv) {
		this.adminSrv=adminSrv;
	}
	
    @GetMapping("/admin")
    public String getAdminPage(HttpSession session, Model model){

        String username = (String)session.getAttribute("username");
     // TEST ADMIN PAGE
        model.addAttribute("username",username);
        return "admin";
    }
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
    @PostMapping("/login")
    public String login(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {
		if (authenticate(admin)) {
			session.setAttribute("username", admin.getUsername());
			return "redirect:/admin";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
    }
    
	private boolean authenticate(Admin admin) {
		String enteredUsername = admin.getUsername();
		String enteredPassword = admin.getPassword();

		Admin actualAdmin = adminSrv.findByUsername(enteredUsername);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (actualAdmin != null) {
			String actualPassword = actualAdmin.getPassword();

			// Compare the entered password with the actual password
			// and return its boolean state
			return encoder.matches(enteredPassword, actualPassword);
		}

		return false; // User not found
	}
	
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/home";
	}
    
	@GetMapping("/taskList")
	public String getAllTask(Model model) {
		model.addAttribute("taskList",adminSrv.getAllTasks());
		return "task-list";
		
	}
	@GetMapping("/task/create")
	public String createTaskFom(Model model) {
		model.addAttribute("task",new Task());
		return "task-create";
	}
    @PostMapping("/task/create")
    public String createTaskSubmit(@ModelAttribute("task")Task task) {
        adminSrv.create(task);
        return "redirect:/taskList";
        
    }
    @GetMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable("id") Long id) {
    	adminSrv.delete(id);
    	 return "redirect:/taskList";
    }
    @GetMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model){
        Optional<Task> task = adminSrv.findbyId(id);
     if (task.isPresent()) {
        model.addAttribute("task", task.get());
        return "task-update";                  
       } 
     return "errorpage";
 
    }
    @PostMapping(value = "/update/{id}")
    public String updateTaskSubmit(@PathVariable("id") long id,
                                   @ModelAttribute("task") Task task) {
        adminSrv.update(task);
        return "redirect:/taskList";
       
    }
}


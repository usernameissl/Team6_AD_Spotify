package iss.ad.project.spotify.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import iss.ad.project.spotify.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import iss.ad.project.spotify.model.Admin;
import iss.ad.project.spotify.model.Task;
import iss.ad.project.spotify.service.AdminService;

@Controller
public class AdminController {
	private final AdminService adminSrv;
    private final TaskService taskSrv;
	
	@Autowired
	public AdminController(AdminService adminSrv, TaskService taskSrv) {
		this.adminSrv=adminSrv;
        this.taskSrv = taskSrv;
	}


	
	@GetMapping("/admin/login")
	public String login() {
		return "login";
	}
    @PostMapping("/admin/login")
    public String login(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {
		if (authenticate(admin)) {
			session.setAttribute("username", admin.getUsername());
			return "redirect:/admin/backtracks";
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
			return encoder.matches(enteredPassword, actualPassword);
		}

		return false; // User not found
	}
	
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("username");
    	session.invalidate();
        return "redirect:/home";
	}
    
	@GetMapping("/admin/taskList")
	public String getAllTask(Model model) {
		model.addAttribute("taskList",adminSrv.getAllTasks());
		return "task-list";
		
	}
	@GetMapping("admin/task/create")
	public String createTaskFom(Model model) {
		model.addAttribute("task",new Task());
		return "task-create";
	}
    @PostMapping("admin/task/create")
    public String createTaskSubmit(@ModelAttribute("task")Task task,
                                   BindingResult result,
                                    Model model) {
    	List<Task> taskList = adminSrv.getAllTasks();
        boolean nameExists = false;

        for (Task t : taskList) {
            if (t.getName().equalsIgnoreCase(task.getName())) {
                nameExists = true;
                break;
            }
        }

        if (nameExists || result.hasErrors()) {
            String messageError = "This task name already exists.";
            model.addAttribute("messageError", messageError);
        } 
        else {
            adminSrv.create(task);
            taskSrv.refreshCache();
            String messageSuccess = "Task has been successfully created!";
            model.addAttribute("messageSuccess", messageSuccess);
        }
        model.addAttribute("task", task);
        return "task-create";
             
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteTaskById(@PathVariable("id") Long id) {
    	adminSrv.delete(id);
    	 return "redirect:/admin/taskList";
    }
    @GetMapping("/admin/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model){
        Optional<Task> task = adminSrv.findbyId(id);
     if (task.isPresent()) {
        model.addAttribute("task", task.get());
        return "task-update";                  
       } 
     return "errorpage";
 
    }
    @PostMapping(value = "/admin/update/{id}")
    public String updateTaskSubmit(@Valid @PathVariable("id") long id,
                                   @ModelAttribute("task") Task task,
                                   BindingResult result,
                                   Model model) {
    	List<Task> taskList = adminSrv.getAllTasks();
        boolean nameExists = false;

        for (Task t : taskList) {
            if (t.getName().equalsIgnoreCase(task.getName())) {
                nameExists = true;
                break;
            }
        }

        if (nameExists || result.hasErrors()) {
            String messageError = "This task name already exists.";
            model.addAttribute("messageError", messageError);
        } 
        else {
            adminSrv.update(task);
            taskSrv.refreshCache();
            String messageSuccess = "Task has been successfully updated!";
            model.addAttribute("messageSuccess", messageSuccess);
        }
        model.addAttribute("task", task);
        return "task-update";
             
    }
}


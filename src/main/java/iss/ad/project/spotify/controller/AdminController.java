package iss.ad.project.spotify.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import iss.ad.project.spotify.model.Task;
import iss.ad.project.spotify.service.AdminService;

@Controller
public class AdminController {
	private final AdminService adminSrv;
	
	@Autowired
	public AdminController(AdminService adminSrv) {
		this.adminSrv=adminSrv;
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
	



	


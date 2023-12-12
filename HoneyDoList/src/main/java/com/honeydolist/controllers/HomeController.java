package com.honeydolist.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honeydolist.models.LoginUser;
import com.honeydolist.models.User;
import com.honeydolist.models.Task;
import com.honeydolist.services.TaskService;
import com.honeydolist.services.UserService;
//import com.kickballleague.models.LoginUser;
//import com.kickballleague.models.User;
//import com.kickballleague.models.task;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	UserService userServ;
	
	@Autowired
	TaskService taskServ;
	
	@GetMapping("/")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
    	//run register method from user service
    	User user = userServ.register(newUser, result);
    	 // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
    	
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        
        session.setAttribute("userId", newUser.getId());
    
        return "redirect:/home";
    }
	
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
    
        return "redirect:/home";
    }
    
    @GetMapping("/home") 
    public String show(Model model, HttpSession session) {
    	
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	model.addAttribute("tasks", taskServ.allTasks());
    	model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
    	return "Show.jsp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.setAttribute("userId", null);
    	return "redirect:/";
    }
    
    //Display tasks on "/home" link
    @RequestMapping("/home")
	public String alltasks(Model model,HttpSession session) {
    	System.out.println(session);
    	System.out.println("STUFF-----------------------------------");
		ArrayList<Task> tasks = (ArrayList<Task>) taskServ.allTasks();
		model.addAttribute("tasks", tasks);
		
		return "Show.jsp";
	}
    
    //Display tasks/new webpage
    @GetMapping("/tasks/new")
	public String newTask1(@ModelAttribute("task") Task task, Model model, HttpSession session) {
		
		User user = userServ.findById((Long)session.getAttribute("userId"));
		model.addAttribute("user", user);
		return "NewTask.jsp";
	}
    
    //Post request for making new task
    @PostMapping("/tasks/new")
	public String create(
			@Valid @ModelAttribute("task") Task task,
			BindingResult result
			) {
    	
    	
    	
		if (result.hasErrors()) {
			return "NewTask.jsp";
		}
		
		taskServ.createTask(task);
		return "redirect:/home";
	}
    
    @GetMapping("/tasks/{id}")
    public String viewtask(@PathVariable("id") Long id, Model model, HttpSession session) {
  	User user = userServ.findById((Long)session.getAttribute("userId"));
  	
  	if(session.getAttribute("userId") == null) {
  		return "redirect:/";
  	}
  	Task task = taskServ.findTask(id);
  	model.addAttribute("task", task);
  	model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
		
		return "ShowOne.jsp";
  }
    
  @GetMapping("/tasks/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
	
	User user = userServ.findById((Long)session.getAttribute("userId"));
	
	if(session.getAttribute("userId") == null) {
		return "redirect:/";
	}
	Task task = taskServ.findTask(id);
	model.addAttribute("task", task);
	model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));

	return "Edit.jsp";
  	}

  	@RequestMapping(value="/tasks/{id}/edit", method=RequestMethod.PUT)
  	public String update(
		@PathVariable("id") Long id,@Valid @ModelAttribute("task") Task task,
		BindingResult result, Model model, HttpSession session
		) {
	
	if (result.hasErrors()) {
	
  	model.addAttribute("task", task);
  	model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
		return "Edit.jsp";
	}
	User user = userServ.findById((Long)session.getAttribute("userId"));
	taskServ.updateTask(task);
	return "redirect:/home";
	}

  	//Delete a task
  	@RequestMapping(value="/tasks/{id}", method=RequestMethod.DELETE)
  	public String destroy(@PathVariable("id") Long id) {
  		taskServ.deleteTask(id);
  		return "redirect:/home";
  	}
	
}

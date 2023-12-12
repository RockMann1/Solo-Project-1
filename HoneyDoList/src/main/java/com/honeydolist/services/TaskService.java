package com.honeydolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeydolist.models.Task;
import com.honeydolist.repositories.TaskRepository;
//import com.kickballleague.models.Team;
//import com.kickballleague.models.Team;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepo;
	
	public List<Task> allTasks() {
		return taskRepo.findAll();
	}
	
	public Task createTask(Task task) {
		return taskRepo.save(task);
	}
	
	public Task findTask(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		} 
			return null;
	}
	
	//deletes task
    public void deleteTask(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		if(optionalTask.isPresent()) {
			taskRepo.deleteById(id);
		}
	}
    
  //updates a task
    public Task updateTask(Task task) {
    	return taskRepo.save(task);
    }
	
		
}

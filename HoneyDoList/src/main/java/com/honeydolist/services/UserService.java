package com.honeydolist.services;
import com.honeydolist.models.LoginUser;
import com.honeydolist.models.User;
import com.honeydolist.repositories.UserRepository;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

//import com.kickballleague.models.LoginUser;
//import com.kickballleague.models.User;
//import com.kickballleague.repositories.UserRepository;

//import com.kickballleague.repositories.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository repo;
	
	
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		// TO-DO - Reject values or register if no errors:
		// Reject if email is taken (present in database)
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "Email already exists in database!");
    	} 
    	// Reject if password doesn't match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
	    // Return null if result has errors
    	if(result.hasErrors()) {
    	    // Exit the method and go back to the controller 
    	    // to handle the response
    	    return null;
    	}

	        // Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
	        return userRepo.save(newUser); 
	    }
	
	    // This method will be called from the controller
	    // whenever a user submits a login form.
	    public User login(LoginUser newLogin, BindingResult result) {
	    // TO-DO - Reject values:
		// Find user in the DB by email
	    // Reject if NOT present
	    Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
	    if(!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email does not exist in database!");
			return null;
		} 
    
	    // Reject if BCrypt password match fails
	    User user = potentialUser.get();
	    if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
	        result.rejectValue("password", "Matches", "Invalid Password!");
	    }
	    // Return null if result has errors
	    if(result.hasErrors()) {
		    // Exit the method and go back to the controller 
		    // to handle the response
		    return null;
		}
	    // Otherwise, return the user object
	    	return user;
	    }
	    
	    public User findbyEmail(String email) {
	    	Optional<User> result = repo.findByEmail(email);
	    	if(result.isPresent()) {
	    		return result.get();
	    	}
	    	return null;
	    }

	    public User findById(Long id) {
	    	Optional<User> potentialUser = userRepo.findById(id);
	    	if(potentialUser.isPresent()) {
	    		return potentialUser.get();
	    	}
	    	return null;
	    }
}

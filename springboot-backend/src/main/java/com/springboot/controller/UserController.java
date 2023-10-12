package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.User;
import com.springboot.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User>getStudentById(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/users")
	public User CreateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
	    User user = userRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
	    
	    user.setFirstName(userDetails.getFirstName());
	    user.setLastName(userDetails.getLastName());
	    user.setEmailId(userDetails.getEmailId());
	    
	    User updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exits with id :"+id));
		
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}

}

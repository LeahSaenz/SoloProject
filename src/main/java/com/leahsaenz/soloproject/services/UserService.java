package com.leahsaenz.soloproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import com.leahsaenz.soloproject.models.LoginUser;
import com.leahsaenz.soloproject.models.User;
import com.leahsaenz.soloproject.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	//User Registration
	public User register(User newUser, BindingResult result) {
		//Check to see if PWs agree
		if(!newUser.getPassword().equals(newUser.getConfirmPW())) {
			result.rejectValue("password", "Matches", "Passwords must match!");
		}
		//Check to see if email already exists in DB
		Optional<User>foundUser = userRepo.findByEmail(newUser.getEmail());
		if(foundUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email is already in use");
		}
		if(result.hasErrors()) {
			return null;
		} else {
			//Hash and set PW, save user to DB
			String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashedPassword);
			return userRepo.save(newUser);
		}
	}
	
	//Find User by ID
	public User findById(Long id) {
		Optional<User>foundUser = userRepo.findById(id);
		return foundUser.isPresent() ? foundUser.get() : null;
	}
	
	//Find User by Street Address
	public User findByStreetAddress(String streetAddress) {
		Optional<User>foundUser = userRepo.findByStreetAddress(streetAddress);
		return foundUser.isPresent() ? foundUser.get() : null;
	}
	
	//Find All Users
	public List<User>allUsers(){
		return userRepo.findAll();
	}
	
	//User Login
	public User login (LoginUser newLoginObject, BindingResult result) {
		//Find user in DB by email
		Optional<User>foundUser = userRepo.findByEmail(newLoginObject.getEmail());
		//Reject if not present
		if(foundUser.isEmpty()) {
			result.rejectValue("email", "Matches", "Invalid Login Credentials");
			return null;
		} else { //if user is found
			User userFromDb = foundUser.get();
			//Check PW
			if(!BCrypt.checkpw(newLoginObject.getPassword(), userFromDb.getPassword())) {
				result.rejectValue("password", "Matches", "Invalid Login Credentials");
				return null;
			} else {
				return userFromDb;
			}
		}
	}
		//Adding a Customer
		public User addCostumer(User user) {
			return userRepo.save(user);
		}
		
		//All Customers
		public List<User>all(){
			return userRepo.findAll();
		}
		
		//Find Customer by ID
		public User findUser(Long id) {
			Optional<User>optionalUser = userRepo.findById(id);
			if(optionalUser.isPresent()) {
				return optionalUser.get();
			} else {
				return null;
			}
		}
		
		//Get one Customer
		public User getOneUser(Long id) {
			Optional<User>userOrNull = userRepo.findById(id);
			if(userOrNull.isPresent()) {
				return userOrNull.get();
			} else {
				return null;
			}
		}
		
		//Update a Customer
		public User updateUser(User updatedUser) {
			return userRepo.save(updatedUser);
		}
		
		//Delete a Customer
		public void deleteUser(Long id) {
			userRepo.deleteById(id);
		}
}
	



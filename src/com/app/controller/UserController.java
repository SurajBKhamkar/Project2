package com.app.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dao.IUserDao;
import com.app.pojos.Users;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private IUserDao dao;
	
	@PostConstruct
	public void init() {
		//System.out.println("In init() "+ dao);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> validateUser(@RequestBody Users user) {
		Users u = dao.validateUser(user);
		if (u != null) {
			return new ResponseEntity<Users>(u, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/signUp", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signUp(@RequestBody Users signUp) {
		if (signUp!=null) {
			Users regd = dao.registerUser(signUp);
			if(regd != null) {
				return new ResponseEntity<Users> (regd, HttpStatus.OK);
			}
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/signUpAsOwner", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signUpAsOwner(@RequestBody Users signUp) {
		if (signUp!=null) {
			Users regd = dao.registerOwner(signUp);
			if(regd != null) {
				return new ResponseEntity<Users> (regd, HttpStatus.OK);
			}
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
}
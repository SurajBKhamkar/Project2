package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.app.dao.IAdminDao;
import com.app.pojos.Hotel;
import com.app.pojos.Order;
import com.app.pojos.Users;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private IAdminDao dao;
	
	@GetMapping("/list")
	public ResponseEntity<?> listOfUsers() {
		List<Users> list = dao.getUsersList();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Users>> (list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/listOfHotels")
	public ResponseEntity<?> listOfApprovedHotels() {
		List<Hotel> list = dao.getApprovedHotels();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Hotel>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/listOfRequests")
	public ResponseEntity<?> listOfPendingHotels() {
		List<Hotel> list = dao.getPendingHotels();
		return new ResponseEntity<List<Hotel>> (list, HttpStatus.OK);
	}
	
	@PostMapping(path = "deleteHotel", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteHotel(@RequestBody Integer hotel_id) {
		Hotel result = dao.deleteHotel(hotel_id);
		if (result!=null) {
			return new ResponseEntity<Hotel>(result, HttpStatus.OK); 
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/approveHotel", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> approveHotel(@RequestBody Integer hotel_id) {
		Hotel result = dao.approveHotel(hotel_id);
		if (result!=null) {
			return new ResponseEntity<Hotel>(result, HttpStatus.OK); 
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/rejectHotel", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> rejectHotel(@RequestBody Integer hotel_id) {
		Hotel result = dao.rejectHotel(hotel_id);
		if (result!=null) {
			return new ResponseEntity<Hotel>(result, HttpStatus.OK); 
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/todayOrders")
	public ResponseEntity<?> todaysOrders() {
		List<Order> list = dao.getTodaysOrders();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Order>> (list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Order>> (HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/thisMonth")
	public ResponseEntity<?> thisMonth() {
		List<Order> list = dao.getThisMonth();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Order>> (list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Order>> (HttpStatus.NO_CONTENT);
		}
	}
}

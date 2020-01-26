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

import com.app.dao.ICustomerDao;
import com.app.pojos.Address;
import com.app.pojos.Menus;
import com.app.pojos.Order;
import com.app.pojos.OrdersPlusMenu;
import com.app.pojos.Users;
import com.app.pojos.Wrapper;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerDao dao;
	
	@GetMapping(path = "/menuList")
	public ResponseEntity<?> getMenuList() {
		List<Menus> list = dao.getMenuList();
		if (list!=null) {
			return new ResponseEntity<List<Menus>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/listOfOrders", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> listOfOrders(@RequestBody Users u) {
		
		List<OrdersPlusMenu> list = dao.getListOfOrders(u);
		if (list!=null) {
			return new ResponseEntity<List<OrdersPlusMenu>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/getAddresses", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getAddresses(@RequestBody Users u) {
		List<Address> addresses = dao.getAdd(u);
		if (addresses!=null) {
			return new ResponseEntity<List<Address>> (addresses, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/placeOrder", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> placeOrder(@RequestBody Wrapper obj) {
		Order placed = dao.placeOrder(obj);
		if (placed!=null) {
			return new ResponseEntity<Order> (placed, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/searchedMenus", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> searchedMenus(@RequestBody Menus menu) {
		List<Menus> list = dao.getsearchedMenus(menu);
		if (list!=null) {
			return new ResponseEntity<List<Menus>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/cancelOrder", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> cancelOrder(@RequestBody Integer order_id) {
		Order o = dao.cancelOrder(order_id);
		if (o!=null) {
			return new ResponseEntity<Order> (o, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NOT_MODIFIED);
	}
	
	
	@PostMapping(path = "/deleteAddress", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteAddress(@RequestBody Address add) {
		Address o = dao.deleteAddress(add);
		if (o!=null) {
			return new ResponseEntity<Address> (o, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NOT_MODIFIED);
	}
	
	@PostMapping(path = "/addAddressDetails", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addAddressDetails(@RequestBody Address add) {
		Address o = dao.addAddressDetails(add);
		if (o!=null) {
			return new ResponseEntity<Address> (o, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NOT_MODIFIED);
	}
	
	@PostMapping(path = "/saveAddress", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveAddress(@RequestBody Address add) {
		Address o = dao.saveAddress(add);
		if (o!=null) {
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NOT_MODIFIED);
	}
	
}

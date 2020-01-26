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

import com.app.dao.IOwnerDao;
import com.app.pojos.Address;
import com.app.pojos.Hotel;
import com.app.pojos.HotelEnum;
import com.app.pojos.Menus;
import com.app.pojos.Order;
import com.app.pojos.OrdersPlusMenu;
import com.app.pojos.Users;

@RestController
@CrossOrigin
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private IOwnerDao dao;
	
	@PostMapping(path = "/addHotelAddress", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addHotelAddress(@RequestBody Hotel hotel) {
		Address addressOfHotel = hotel.getAddress_id();
		/*	System.out.println(hotel.toString());
		 * 	System.out.println(addressOfHotel.toString()); */
		Hotel finalRegedHotel =  dao.addHotelWithAddress(hotel, addressOfHotel);
		if (finalRegedHotel != null) {
			return new ResponseEntity<Hotel> (finalRegedHotel, HttpStatus.OK);
		}
		return new ResponseEntity<Hotel> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/addRequest", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addRequest(@RequestBody Hotel hotel) {
		hotel.setStatus(HotelEnum.PENDING);
		Address add = hotel.getAddress_id();
		Users user = hotel.getUser_id();
		add.addHotelOwner(user);
		Hotel finalReqHotel = dao.addHotelWithAddress(hotel, add);
		if (finalReqHotel != null) {
			return new ResponseEntity<Hotel> (finalReqHotel, HttpStatus.OK);
		}
		return new ResponseEntity<Hotel> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/listOfHotels", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> listOfHotels(@RequestBody Users owner) {
			List<Hotel> list = dao.getList(owner);
			if (list!=null) {
				return new ResponseEntity<List<Hotel>>(list, HttpStatus.OK);
			}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/addMenu", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addMenu(@RequestBody Menus menu) {
		//System.out.println(menu.toString()+"////////"+menu.getHotel_id().toString());
		Menus finalMenu = dao.addMenu(menu);
		if (finalMenu!=null) {
			return new ResponseEntity<Menus> (menu, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/getHotelObject", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getHotelObject(@RequestBody Hotel hotel) {
		Hotel hotelObject = dao.getHotelObject(hotel.getHotel_id());
		if (hotelObject!=null) {
			return new ResponseEntity<Hotel> (hotelObject, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/getMenus",  consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getMenuListOfPerticularHotel(@RequestBody Hotel hotel) {
		List<Menus> list = dao.getMenuList(hotel);
		if (list!=null) {
			return new ResponseEntity<List<Menus>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path="/editMenu",  consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> editMenu(@RequestBody Menus menu) {
		Menus m  = dao.editMenu(menu);
		if (m!=null) {
			return new ResponseEntity<Menus> (m, HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path="/deleteMenu",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteMenu(@RequestBody Menus menu) {
		try {
			dao.deleteMenu(menu);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	
	@GetMapping(path="/pendingOrders")
	public ResponseEntity<?> pendingOrders() {
		List<OrdersPlusMenu> list = dao.pendingOrders();
		if (list!=null) {
			return new ResponseEntity<List<OrdersPlusMenu>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path="/acceptOrder",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> acceptOrder(@RequestBody OrdersPlusMenu order) {
		Order o = dao.acceptOrder(order);
		if (o!=null) {
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	@PostMapping(path="/deliverOrder",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deliverOrder(@RequestBody OrdersPlusMenu order) {
		Order o = dao.deliverOrder(order);
		if (o!=null) {
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@PostMapping(path="/cancelOrder",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> cancelOrder(@RequestBody OrdersPlusMenu order) {
		Order o = dao.cancelOrder(order);
		if (o!=null) {
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}

	@PostMapping(path="/todaysOrders",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> todaysOrdersByHotel(@RequestBody Hotel hotelID) {
		List<OrdersPlusMenu> list = dao.todaysOrdersByHotel(hotelID);
		if (list!=null) {
			return new ResponseEntity<List<OrdersPlusMenu>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

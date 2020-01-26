package com.app.dao;

import java.util.List;

import com.app.pojos.Hotel;
import com.app.pojos.Order;
import com.app.pojos.Users;

public interface IAdminDao {

	List<Users> getUsersList();

	List<Order> getTodaysOrders();

	List<Order> getThisMonth();

	List<Hotel> getApprovedHotels();

	List<Hotel> getPendingHotels();

	Hotel approveHotel(Integer hotel_id);

	Hotel rejectHotel(Integer hotel_id);

	Hotel deleteHotel(Integer hotel_id);

}

package com.app.dao;

import java.util.List;

import com.app.pojos.Address;
import com.app.pojos.Hotel;
import com.app.pojos.Menus;
import com.app.pojos.Order;
import com.app.pojos.OrdersPlusMenu;
import com.app.pojos.Users;

public interface IOwnerDao {

	List<Hotel> getList(Users owner);

	Hotel addHotelWithAddress(Hotel hotel, Address add);

	Hotel getHotelObject(Integer id);

	Menus addMenu(Menus menu);

	List<Menus> getMenuList(Hotel hotel);

	Menus editMenu(Menus menu);

	void deleteMenu(Menus menu);

	List<OrdersPlusMenu> pendingOrders();

	Order acceptOrder(OrdersPlusMenu order);

	Order deliverOrder(OrdersPlusMenu order);

	Order cancelOrder(OrdersPlusMenu order);

	List<OrdersPlusMenu> todaysOrdersByHotel(Hotel hotelID);
}

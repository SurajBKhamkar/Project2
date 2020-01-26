package com.app.dao;

import java.util.List;

import com.app.pojos.Address;
import com.app.pojos.Menus;
import com.app.pojos.Order;
import com.app.pojos.OrdersPlusMenu;
import com.app.pojos.Users;
import com.app.pojos.Wrapper;

public interface ICustomerDao {

	List<Menus> getMenuList();

	List<OrdersPlusMenu> getListOfOrders(Users u);

	List<Address> getAdd(Users u);

	Order placeOrder(Wrapper obj);

	List<Menus> getsearchedMenus(Menus menu);

	Order cancelOrder(Integer order);

	Address deleteAddress(Address add);

	Address addAddressDetails(Address add);

	Address saveAddress(Address add);
}

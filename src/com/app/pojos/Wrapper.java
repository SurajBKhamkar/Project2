package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

public class Wrapper {
	List<Menus> cartItems = new ArrayList<>();
	Users customer = new Users();
	Integer orderTotal;
	
	public Wrapper() {
		// TODO Auto-generated constructor stub
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public List<Menus> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<Menus> cartItems) {
		this.cartItems = cartItems;
	}

	public Users getCustomer() {
		return customer;
	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Wrapper [cartItems=" + cartItems + ", customer=" + customer + "]";
	}
	
	
}

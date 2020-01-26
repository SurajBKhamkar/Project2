package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderDetails")
public class OrdersPlusMenu {
	private Integer id;
	private Order order_id;
	private Menus menu_id;
	private int quantity;
	
	public OrdersPlusMenu() {
		// TODO Auto-generated constructor stub
	}

	public OrdersPlusMenu(int quantity) {
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="order_id")
	public Order getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Order order_id) {
		this.order_id = order_id;
	}

	@ManyToOne
	@JoinColumn(name="menu_id")
	public Menus getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Menus menu_id) {
		this.menu_id = menu_id;
	}

	@Column(name="quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrdersPlusMenu [id=" + id + ", order_id=" + order_id + ", menu_id=" + menu_id + ", quantity=" + quantity
				+ "]";
	}

	public void addData(Order order, Menus menus) {
		this.order_id = order;
		this.menu_id = menus;
		this.quantity = 1;
	}
}

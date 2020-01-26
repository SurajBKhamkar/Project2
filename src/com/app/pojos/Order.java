package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="orders")
public class Order {
	private Integer order_id;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	private LocalDate date;
	@JsonDeserialize(using = LocalTimeDeserializer.class)  
	@JsonSerialize(using = LocalTimeSerializer.class)  
	private LocalTime time;
	private double amount;
	private PaymentsMode type;
	private Users oUserId;
	private OrderEnum status;
	@JsonIgnore
	private List<OrdersPlusMenu> ordersPlusMenu = new ArrayList<>();
	
	public Order() {
	}

	public Order(LocalDate date, LocalTime time, double amount, PaymentsMode type, OrderEnum status) {
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.type = type;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	@Column(name="date")
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Column(name="time")
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="type")
	public PaymentsMode getType() {
		return type;
	}

	public void setType(PaymentsMode type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public Users getoUserId() {
		return oUserId;
	}

	public void setoUserId(Users oUserId) {
		this.oUserId = oUserId;
	}
	
	@OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL)	
	public List<OrdersPlusMenu> getOrdersPlusMenu() {
		return ordersPlusMenu;
	}

	public void setOrdersPlusMenu(List<OrdersPlusMenu> ordersPlusMenu) {
		this.ordersPlusMenu = ordersPlusMenu;
	}

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	public OrderEnum getStatus() {
		return status;
	}

	public void setStatus(OrderEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", date=" + date + ", time=" + time + ", amount=" + amount + ", type=" + type + "]";
	}
}

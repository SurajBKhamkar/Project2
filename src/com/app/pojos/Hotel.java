package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="hotel")
public class Hotel {
	private Integer hotel_id;
	private String name, email, phone;
	private int rating;
	private Address address_id;
	private Users user_id;
	private HotelEnum status;
	
	public Hotel() {
	}

	public Hotel(String name, String email, String phone, int rating) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.rating = rating;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotel_id")
	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Column(name="name", length = 30)
	public String getName() {
		return name;
	}

	public void setName(String hotelName) {
		this.name = hotelName;
	}

	@Column(name="email", length = 25, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String hotelEmail) {
		this.email = hotelEmail;
	}

	@Column(name="phone", nullable = false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String hotelPhone) {
		this.phone = hotelPhone;
	}

	@Column(name="rating")
	public int getRating() {
		return rating;
	}

	public void setRating(int hotelRating) {
		this.rating = hotelRating;
	}

	@OneToOne
	@JoinColumn(name="address_id")
	public Address getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Address address_id) {
		this.address_id = address_id;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	public Users getUser_id() {
		return user_id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public HotelEnum getStatus() {
		return status;
	}

	public void setStatus(HotelEnum status) {
		this.status = status;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}
	
	public void addHotelOwner(Users u) {
		this.setUser_id(u);
	}

	@Override
	public String toString() {
		return "Hotel [hotel_id=" + hotel_id + ", hotelName=" + name + ", hotelEmail=" + email
				+ ", hotelPhone=" + phone + ", hotelRating=" + rating + "]";
	}
	
	
	
	
}

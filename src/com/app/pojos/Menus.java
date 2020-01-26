package com.app.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="menus")
public class Menus {
	private Integer menu_id;
	private Hotel hotel_id;
	private String name, type;
	private int rating;
	private double price;
	@JsonIgnore
	private byte[] image;
	@JsonIgnore
	private List<OrdersPlusMenu> mOrdersPlusMenus = new ArrayList<>();
	
	public Menus() {
	}

	public Menus(String name, String type, double price, int rating) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.rating = rating;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="menu_id")
	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	@OneToOne
	@JoinColumn(name="hotel_id")
	public Hotel getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Hotel hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Column(name="name", length=25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="rating")
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Column(name="image", columnDefinition = "longblob")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@OneToMany(mappedBy = "menu_id", cascade = CascadeType.ALL)
	public List<OrdersPlusMenu> getmOrdersPlusMenus() {
		return mOrdersPlusMenus;
	}

	public void setmOrdersPlusMenus(List<OrdersPlusMenu> mOrdersPlusMenus) {
		this.mOrdersPlusMenus = mOrdersPlusMenus;
	}

	@Override
	public String toString() {
		return "Menus [menu_id=" + menu_id + ", name=" + name + ", type=" + type + ", rating=" + rating + ", price="
				+ price + ", image=" + Arrays.toString(image) + "]";
	}

	public void addHotelToMenu(Hotel hotel) {
		this.setHotel_id(hotel);		
	}	
}

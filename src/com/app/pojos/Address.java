
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
@Table(name="address")
public class Address {
	private Integer address_id;
	private String flat, building, landmark, area, city, state;
	private int zip;
	private Users addOfUser;
	
	public Address() {
	}

	public Address(String flat, String building, String landmark, String area, String city, String state, int zip) {
		this.flat = flat;
		this.building = building;
		this.landmark = landmark;
		this.area = area;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	public Integer getId() {
		return address_id;
	}

	public void setId(Integer id) {
		this.address_id = id;
	}

	@Column(name="flat", length = 20)
	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	@Column(name="building", length = 20)
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	@Column(name="landmark", length = 20)
	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@Column(name="area", length = 20)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name="city", length = 20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="state", length = 20)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="zip")
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public Users getAddOfUser() {
		return addOfUser;
	}

	public void setAddOfUser(Users addOfUser) {
		this.addOfUser = addOfUser;
	}
	public void addHotelOwner(Users u) {
		this.setAddOfUser(u);
	}

	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", flat=" + flat + ", building=" + building + ", landmark="
				+ landmark + ", area=" + area + ", city=" + city + ", state=" + state + ", zip=" + zip + ", addOfUser="
				+ addOfUser + "]";
	}

	
}

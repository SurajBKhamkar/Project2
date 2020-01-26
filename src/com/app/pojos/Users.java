package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class Users {
	private Integer user_id;
	private String name, email, password;
	private String mobile;
	private UserEnum role;
	@JsonIgnore
	private List<Address> addresses = new ArrayList<>();
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	
	public Users() {
		
	}

	public Users(String name, String email, String password, String mobile) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
		
	}

	@Column(length = 30, name="name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 30, name="email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 20, name="password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 15, name="mobile", nullable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(length = 10, name = "role")
	@Enumerated(EnumType.STRING)
	public UserEnum getRole() {
		return role;
	}

	public void setRole(UserEnum role) {
		this.role = role;
	}

	@OneToMany(mappedBy = "addOfUser",cascade = CascadeType.ALL,orphanRemoval = true)
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(mappedBy = "oUserId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	void addAddress(Address a) {
		addresses.add(a);
		a.setAddOfUser(this);
	}
	
	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobile=" + mobile + ", role=" + role + "]";
	}
}

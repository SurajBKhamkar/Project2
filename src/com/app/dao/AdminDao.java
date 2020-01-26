package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Hotel;
import com.app.pojos.HotelEnum;
import com.app.pojos.Order;
import com.app.pojos.Users;

@Transactional
@Repository
public class AdminDao implements IAdminDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Users> getUsersList() {
		String jpql = "select u from Users u";
		return sf.getCurrentSession().createQuery(jpql, Users.class).getResultList();
	}

	@Override
	public List<Order> getTodaysOrders() {
		LocalDate date = LocalDate.now();	
		String jpql = "select o from Order o where o.date=:d";
		return sf.getCurrentSession().createQuery(jpql, Order.class)
				.setParameter("d", date).getResultList();
	}

	@Override
	public List<Order> getThisMonth() {
		LocalDate date = LocalDate.now();
		String jpql = "select o from Order o where MONTH(o.date)=MONTH(:d)";
		return sf.getCurrentSession().createQuery(jpql, Order.class)
				.setParameter("d", date).getResultList();
	}

	@Override
	public List<Hotel> getApprovedHotels() {
		String jpql = "select h from Hotel h";
		return sf.getCurrentSession().createQuery(jpql, Hotel.class).getResultList();
	}

	@Override
	public List<Hotel> getPendingHotels() {
		HotelEnum st = HotelEnum.PENDING;
		String jpql = "select h from Hotel h where h.status=:s";
		return sf.getCurrentSession().createQuery(jpql, Hotel.class).setParameter("s", st).getResultList();
	}

	@Override
	public Hotel approveHotel(Integer hotel_id) {
		Session session = sf.getCurrentSession();
		Hotel toBeUpdate = session.get(Hotel.class, hotel_id);
		toBeUpdate.setStatus(HotelEnum.APPROVED);
		session.update(toBeUpdate);
		if (toBeUpdate.getStatus()==HotelEnum.APPROVED) {
			return toBeUpdate;
		}
		return null;
	}

	@Override
	public Hotel rejectHotel(Integer hotel_id) {
		Session session = sf.getCurrentSession();
		Hotel toBeUpdate = session.get(Hotel.class, hotel_id);
		toBeUpdate.setStatus(HotelEnum.REJECTED);
		session.update(toBeUpdate);
		if (toBeUpdate.getStatus()==HotelEnum.REJECTED) {
			return toBeUpdate;
		}
		return null;

	}

	@Override
	public Hotel deleteHotel(Integer hotel_id) {
		Session session = sf.getCurrentSession();
		Hotel h = session.get(Hotel.class, hotel_id);
		if (h!=null) {
			session.delete(h);
			return h;
		}
		return null;
	}

}

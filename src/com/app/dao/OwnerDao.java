package com.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.Hotel;
import com.app.pojos.Menus;
import com.app.pojos.Order;
import com.app.pojos.OrderEnum;
import com.app.pojos.OrdersPlusMenu;
import com.app.pojos.Users;

@Transactional
@Repository
public class OwnerDao implements IOwnerDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Hotel> getList(Users owner) {
		String jpql = "select h from Hotel h where h.user_id=' "+owner.getUser_id()+"'";
		return sf.getCurrentSession().createQuery(jpql, Hotel.class).getResultList();
	}
	
	@Override
	public Hotel addHotelWithAddress(Hotel hotel, Address add) {
		Session session = sf.getCurrentSession();
		session.save(add);
		Hotel h = (Hotel) session.merge(hotel);
		return h;
	}

	@Override
	public Hotel getHotelObject(Integer id) {
		return sf.getCurrentSession().get(Hotel.class, id);
	}

	@Override
	public Menus addMenu(Menus menu) {
		Session session = sf.getCurrentSession();
		menu.addHotelToMenu(menu.getHotel_id());
		Menus mm = (Menus) session.merge(menu);
		if(mm!=null) {
			return mm;
		}
		return null;
	}

	@Override
	public List<Menus> getMenuList(Hotel hotel) {
		String jpql = "select m from Menus m where m.hotel_id='"+ hotel.getHotel_id() +"' ";
		return sf.getCurrentSession().createQuery(jpql, Menus.class).getResultList();
	}

	@Override
	public Menus editMenu(Menus menu) {
		System.out.println(menu.toString());
		return (Menus) sf.getCurrentSession().merge(menu);
	}

	@Override
	public void deleteMenu(Menus menu) {
		sf.getCurrentSession().delete(menu);
	}

	@Override
	public List<OrdersPlusMenu> pendingOrders() {
		String jpql = "select o from OrdersPlusMenu o where o.order_id.status='"+OrderEnum.PENDING+"' or o.order_id.status='"+OrderEnum.ACCEPTED+"'";
		return sf.getCurrentSession().createQuery(jpql, OrdersPlusMenu.class).getResultList();
	}

	@Override
	public Order acceptOrder(OrdersPlusMenu ordersPlusMenu) {
		Order o = ordersPlusMenu.getOrder_id();
		o.setStatus(OrderEnum.ACCEPTED);
		sf.getCurrentSession().update(o);
		return o;
	}

	@Override
	public Order deliverOrder(OrdersPlusMenu ordersPlusMenu) {
		Order o = ordersPlusMenu.getOrder_id();
		o.setStatus(OrderEnum.DELIVERED);
		sf.getCurrentSession().update(o);
		return o;
	}

	@Override
	public Order cancelOrder(OrdersPlusMenu ordersPlusMenu) {
		Order o = ordersPlusMenu.getOrder_id();
		o.setStatus(OrderEnum.CANCELLED);
		sf.getCurrentSession().update(o);
		return o;
	}

	@Override
	public List<OrdersPlusMenu> todaysOrdersByHotel(Hotel hotelID) {
		Session session = sf.getCurrentSession();
		Hotel h = session.get(Hotel.class, hotelID.getHotel_id());
		String jpql = "select o from OrdersPlusMenu o where o.menu_id.hotel_id='"+h.getHotel_id()+"'";
		return session.createQuery(jpql, OrdersPlusMenu.class).getResultList();
	}
}

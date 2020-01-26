package com.app.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.HotelEnum;
import com.app.pojos.Menus;
import com.app.pojos.Order;
import com.app.pojos.OrderEnum;
import com.app.pojos.OrdersPlusMenu;
import com.app.pojos.PaymentsMode;
import com.app.pojos.Users;
import com.app.pojos.Wrapper;

@Repository
@Transactional
public class CustomerDao implements ICustomerDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public List<Menus> getMenuList() {
		String jpql = "select m from Menus m where m.hotel_id.status='"+HotelEnum.APPROVED+"'";
		return sf.getCurrentSession().createQuery(jpql, Menus.class).getResultList();
	}

	@Override
	public List<OrdersPlusMenu> getListOfOrders(Users u) {
		String jpql = "select o from OrdersPlusMenu o where o.order_id.oUserId= '"+u.getUser_id()+"' ";
		return sf.getCurrentSession().createQuery(jpql, OrdersPlusMenu.class).getResultList();
	}

	@Override
	public List<Address> getAdd(Users u) {
		String jpql = "select a from Address a where a.addOfUser = '"+u.getUser_id()+"'";
		return sf.getCurrentSession().createQuery(jpql, Address.class).getResultList();
	}

	
	//QUANTITY REMAINING
	@Override
	public Order placeOrder(Wrapper obj) {
		List<Menus> list = obj.getCartItems();
		Users customer = obj.getCustomer();
		Order order = new Order(LocalDate.now(), LocalTime.now(), obj.getOrderTotal(), PaymentsMode.UPI, OrderEnum.PENDING);
		order.setoUserId(customer);
		Session session = sf.getCurrentSession();
		Order o = (Order) session.merge(order);
		for (Menus menus : list) {
			OrdersPlusMenu placing = new OrdersPlusMenu();
			placing.addData(o, menus);
			session.merge(placing);
		}
		return o;
	}

	@Override
	public List<Menus> getsearchedMenus(Menus menu) {
		String jpql = "select m from Menus m where m.name='"+menu.getName()+"'";
		return sf.getCurrentSession().createQuery(jpql, Menus.class).getResultList();
	}

	@Override
	public Order cancelOrder(Integer order_id) {
		Order o = sf.getCurrentSession().get(Order.class, order_id);
		o.setStatus(OrderEnum.CANCELLED);
		sf.getCurrentSession().update(o);
		return sf.getCurrentSession().get(Order.class, o.getOrder_id());
	}

	@Override
	public Address deleteAddress(Address add) {
		sf.getCurrentSession().delete(add);
		return add;
	}

	@Override
	public Address addAddressDetails(Address add) {
		return (Address) sf.getCurrentSession().merge(add);
	}

	@Override
	public Address saveAddress(Address add) {
		sf.getCurrentSession().update(add);
		return add;
	}

}

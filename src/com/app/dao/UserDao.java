package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.UserEnum;
import com.app.pojos.Users;

@Transactional
@Repository
public class UserDao implements IUserDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public List<Users> getAllUsers() {
		String jpql = "select u from Users u";
		return sf.getCurrentSession().createQuery(jpql, Users.class).getResultList();
	}

	@Override
	public Users validateUser(Users u) {
		String jpql = "select u from Users u where u.email=:em and u.password=:pass";
		return sf.getCurrentSession().createQuery(jpql, Users.class)
				.setParameter("em", u.getEmail())
				.setParameter("pass", u.getPassword())
				.getSingleResult();
	}

	@Override
	public Users registerUser(Users signUp) {
		signUp.setRole(UserEnum.CUSTOMER);
		Users regd = (Users) sf.getCurrentSession().merge(signUp);
		if (regd != null) {
			return regd;
		}
		return null;
	}

	@Override
	public Users registerOwner(Users signUp) {
		signUp.setRole(UserEnum.OWNER);
		Users regd = (Users) sf.getCurrentSession().merge(signUp);
		if (regd != null) {
			return regd;
		}
		return null;
	}	
}

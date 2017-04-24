package com.jstudio.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jstudio.model.Airport;
import com.jstudio.model.Flight;
import com.jstudio.model.Person;
import com.jstudio.model.Reservation;
import com.jstudio.model.Rout;
import com.jstudio.model.User;
import com.jstudio.model.UserRole;

public class ObjectDAOImpl<T> implements ObjectDAO<T>{

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void save(T objectToSave) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(objectToSave);
		tx.commit();
		session.close();

	}

	public List<T> list(T objectToCheckType) {
		Session session = this.sessionFactory.openSession();

		String objectType = checkObjectType(objectToCheckType);
		List list = session.createQuery("from " + objectType).list();

		session.close();
		return list;
	}

	public List getFlights(){

		Session session = this.sessionFactory.openSession();

		List list = session.createQuery("from Flight f where f.idflight='4'").list();

		session.close();
		return list;
	}

	public List<T> getFlightsWithRout(Rout rout){

		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Flight a where a.rout=:name");
		q.setParameter("name", rout);
		List<T> list = q.list();

		session.close();
		return list;
	}

	public Airport getAirport(String airportName){
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Airport a where a.city=:name");
		q.setParameter("name", airportName);
		List<Airport> list = q.list();

		Airport airport = list.get(0);

		session.close();
		return airport;
	}

	public Rout getRout(Airport fromAirport, Airport toAirport){
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Rout a where a.from_airport=:name1 and a.to_airport=:name2");
		q.setParameter("name1", fromAirport);
		q.setParameter("name2", toAirport);
		List<Rout> list = q.list();

		Rout rout = list.get(0);

		session.close();
		return rout;
	}

	public void deleteUser(UserRole userRole){
		Session session = this.sessionFactory.openSession();

		User user = new User();
		user.setUsername(userRole.getUser().getUsername());
		session.update(user);

//		UserRole userRole = (UserRole)session.load(UserRole.class, Integer.parseInt(id));
		System.out.println("pp " + userRole.getUser() + " " + userRole.getUserRoleId());
//		User user = userRole.getUser();
//		session.delete(userRole);
		session.delete(user);

		session.close();
	}

	private String checkObjectType(T objectToCheckType){

		String objectType = null;

		if (objectToCheckType instanceof Person) {
			objectType = "Person";
		} else if (objectToCheckType instanceof Airport){
			objectType = "Airport";
		} else if (objectToCheckType instanceof Flight){
			objectType = "Flight";
		} else if (objectToCheckType instanceof Reservation){
			objectType = "Reservation";
		} else if (objectToCheckType instanceof Rout){
			objectType = "Rout";
		} else if (objectToCheckType instanceof User){
			objectType = "User";
		} else if (objectToCheckType instanceof UserRole){
			objectType = "UserRole";
		} else {
			objectType = "";
		}

		return objectType;
	}

	@Override
	public Flight getFlight(String flightID) {
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Flight a where a.idflight=:name");
		q.setParameter("name", Integer.parseInt(flightID));
		List<Flight> list = q.list();

		Flight flight = list.get(0);

		session.close();
		return flight;
	}

	@Override
	public User getUser(String username) {
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from User a where a.username=:name");
		q.setParameter("name", username);
		List<User> list = q.list();

		User user = list.get(0);

		session.close();
		return user;
	}

	@Override
	public List<Reservation> getReservations(User user) {
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Reservation a where a.username=:name");
		q.setParameter("name", user);
		List<Reservation> list = q.list();

		session.close();
		return list;
	}

	@Override
	public List<Rout> getRoutWhereFromAirport(Airport fromAirport) {
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Rout a where a.from_airport=:name");
		q.setParameter("name", fromAirport);
		List<Rout> list = q.list();

		session.close();
		return list;
	}

	@Override
	public List<Rout> getRoutWhereToAirport(Airport toAirport) {
		Session session = this.sessionFactory.openSession();

		Query q = session.createQuery("from Rout a where a.to_airport=:name");
		q.setParameter("name", toAirport);
		List<Rout> list = q.list();

		session.close();
		return list;
	}
}

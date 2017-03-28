package com.jstudio.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jstudio.model.Airport;
import com.jstudio.model.Flight;
import com.jstudio.model.Person;
import com.jstudio.model.Reservation;
import com.jstudio.model.Rout;

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
		System.out.println("Dostalem sie do list w DAO");
		System.out.println("objectToCheckType " + objectToCheckType.toString());
		Session session = this.sessionFactory.openSession();

		String objectType = checkObjectType(objectToCheckType);
		System.out.println("yy objectType " + objectType);
//objectToCheckType.getClass().getSimpleName()
	/*	List list = new ArrayList();
		list.add(new Airport("ooo", "p"));*/
		List list = session.createQuery("from " + objectType).list();

		session.close();
		return list;
	}

	public List getFlights(String fromAirport, String toAirport){

		Session session = this.sessionFactory.openSession();

		String idflight = "3";
		List<Flight> list = session.createQuery("FROM Flight f").list();
		session.close();

		return list;
	}

	private String checkObjectType(T objectToCheckType){

		System.out.println("ss objectToCheckType " + objectToCheckType.toString());
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
		} else {
			objectType = "";
		}

		System.out.println("ss objectType " + objectType);
		return objectType;
	}

}

package com.jstudio.dao;

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

public class ObjectDAOImpl implements ObjectDAO<Object>{

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void save(Object objectToSave) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(objectToSave);
		tx.commit();
		session.close();

	}

	public List<Object> list(Object objectToCheckType) {
		Session session = this.sessionFactory.openSession();

		String objectType = checkObjectType(objectToCheckType);

		List<Object> list = session.createQuery("from " + objectType).list();
		session.close();
		return list;
	}

	public List getFlights(String fromAirport, String toAirport){

		Session session = this.sessionFactory.openSession();

		List<Object> list = session.createQuery(
				"FROM Flight f1 INNER JOIN f1.idrout r1 ON f1.idrout = r1.idrout"
				).list();
		session.close();

		return list;
	}

	private String checkObjectType(Object objectToCheckType){

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

		return objectType;
	}

}

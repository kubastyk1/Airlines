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

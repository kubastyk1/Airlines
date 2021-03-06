package com.jstudio.dao;

import java.util.List;

import com.jstudio.model.Airport;
import com.jstudio.model.Flight;
import com.jstudio.model.Reservation;
import com.jstudio.model.Rout;
import com.jstudio.model.User;
import com.jstudio.model.UserRole;

public interface ObjectDAO<T> {

	public void save(T objectToSave);
	public List<T> list(T objectToCheckType);
	public List<T> getFlights();
	public Airport getAirport(String airportName);
	public Rout getRout(Airport fromAirport, Airport toAirport);
	public List<T> getFlightsWithRout(Rout routID);
	public void deleteUser(UserRole userRole);
	public Flight getFlight(String flightID);
	public User getUser(String username);
	public List<Reservation> getReservations(User user);
	public List<Rout> getRoutWhereFromAirport(Airport fromAirport);
	public List<Rout> getRoutWhereToAirport(Airport toAirport);
}

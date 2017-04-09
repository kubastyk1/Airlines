package com.jstudio.dao;

import java.util.List;

import com.jstudio.model.Airport;
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
}

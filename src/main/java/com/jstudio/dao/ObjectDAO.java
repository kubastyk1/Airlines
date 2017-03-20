package com.jstudio.dao;

import java.util.List;

public interface ObjectDAO<T> {

	public void save(T objectToSave);
	public List<T> list(T objectToCheckType);
	public List<T> getFlights(String fromAirport, String toAirport);
}

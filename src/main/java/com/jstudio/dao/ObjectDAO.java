package com.jstudio.dao;

import java.util.List;

public interface ObjectDAO<T> {

	public void save(T objectToSave);
	public List<T> list(T objectToCheckType);
}

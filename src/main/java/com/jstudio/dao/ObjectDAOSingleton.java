package com.jstudio.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ObjectDAOSingleton {

	private static ObjectDAOSingleton objectDAOSingleton;
	private static ObjectDAO objectDAO;

	private ObjectDAOSingleton() {	}

	public static ObjectDAOSingleton getObjectDAOSingleton() {
		if (objectDAOSingleton == null) {
			objectDAOSingleton = new ObjectDAOSingleton();
		}
		return objectDAOSingleton;
	}

	public ObjectDAO getObjectDAO(){

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("HibernateConfig.xml");
		objectDAO = context.getBean(ObjectDAO.class);
		context.close();

		return objectDAO;
	}
}

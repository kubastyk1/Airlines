package com.jstudio.airlines;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jstudio.dao.ObjectDAO;
import com.jstudio.model.Airport;
import com.jstudio.model.Flight;
import com.jstudio.model.Rout;
import com.jstudio.model.User;
import com.jstudio.model.UserRole;

public class DBInit {

	private static ObjectDAO objectDAO;


	public DBInit(ObjectDAO objectDAO) {

		this.objectDAO = objectDAO;
	}

	public void initDatabase() throws ParseException{

		addAirportToQuery();
		addRoutToQuery();
		addFlightToQuery();
		addUsersToQuery();

		System.out.println("Database successfully initialized!");
	}

	public <T> void addRecord(T record){
		objectDAO.save(record);
	}

	public <T> List getRecords(T objectType){

		List<T> list = objectDAO.list(objectType);

		return list;
	}

	public <T> void showRecords(T objectType){

		List<T> list = objectDAO.list(objectType);

		for(T a : list){
			System.out.println("List:: " + a);
		}
	}

	public void addFlightToQuery() throws ParseException{

		List<Rout> routList = getRecords(new Rout());
		int flightNum = 11000;
		double cost;
		for(Rout rout : routList){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = "2017-04-20 17:00:00";
			Date term = null;
			String endDateString = "2017-05-30 17:00:00";
			Date endDate = dateFormat.parse(endDateString);

			while(true){
				cost = ThreadLocalRandom.current().nextDouble(30, 400 + 1);
				DecimalFormat df = new DecimalFormat("#.##");
				String costString = df.format(cost);
				String replece = costString.replaceAll(",", ".");
				cost = Double.parseDouble(replece);
				flightNum++;
				String flightNumber = "LOT" + flightNum;

				Calendar c = Calendar.getInstance();
				c.setTime(dateFormat.parse(date));
				int day = ThreadLocalRandom.current().nextInt(1, 3 + 1);
				int hour = ThreadLocalRandom.current().nextInt(1, 12 + 1);
				int minute = ThreadLocalRandom.current().nextInt(1, 40 + 1);
				c.add(Calendar.DATE, day);
				c.add(Calendar.HOUR, hour);
				c.add(Calendar.MINUTE, minute);
				date = dateFormat.format(c.getTime());

//				System.out.println(date);

				term = dateFormat.parse(date);
				Flight flight = new Flight(term, flightNumber, rout, cost);

//				System.out.println(flight);

				objectDAO.save(flight);

				if(term.after(endDate)){
					break;
				}
			}
		}

	}

	public void addAirportToQuery(){

		HashMap<String, String> airlinesMap = new HashMap<String, String>();
		airlinesMap.put("Katowice", "KTW");
		airlinesMap.put("Gdansk", "GDN");
		airlinesMap.put("Lublin", "LUZ");
		airlinesMap.put("Olsztyn-Mazury", "SZY");
		airlinesMap.put("Poznan", "POZ");
		airlinesMap.put("Szczecin", "SZZ");
		airlinesMap.put("Warszawa-Chopin", "WAW");
		airlinesMap.put("Wroclaw", "WRO");
		airlinesMap.put("Warszawa-Modlim", "WMI");
		airlinesMap.put("Lodz", "LCJ");
		airlinesMap.put("Radom", "RDO");
		airlinesMap.put("Rzeszow", "RZE");
		airlinesMap.put("Bydgoszcz", "BZG");

		for (Entry<String, String> entry : airlinesMap.entrySet())
		{
			Airport airport = new Airport(entry.getKey(), entry.getValue());
			objectDAO.save(airport);
		}
	}

	public void addRoutToQuery(){

		List<Airport> list = getRecords(new Airport());

		for(Airport airport: list){
			for(Airport airport2 : list){
				if(airport.getIdairport() != airport2.getIdairport()){

					Rout rout = new Rout(airport, airport2);
					objectDAO.save(rout);
				}
			}
		}
	}

	public void addUsersToQuery(){

		User admin = new User("admin", "$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y", true);
		User alex = new User("alex", "$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y", true);

		UserRole userRole1 = new UserRole(admin, "ROLE_USER");
		UserRole userRole2 = new UserRole(admin, "ROLE_ADMIN");
		UserRole userRole3 = new UserRole(alex, "ROLE_USER");

		objectDAO.save(admin);
		objectDAO.save(alex);
		objectDAO.save(userRole1);
		objectDAO.save(userRole2);
		objectDAO.save(userRole3);
	}

}

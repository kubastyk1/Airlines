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

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jstudio.dao.ObjectDAO;
import com.jstudio.model.Airport;
import com.jstudio.model.Flight;
import com.jstudio.model.Rout;

public class DBInit {

	private static ObjectDAO objectDAO;

	public static void main(String[] args) throws ParseException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("HibernateConfig.xml");

		objectDAO = context.getBean(ObjectDAO.class);

		addAirportToQuery();
		addRoutToQuery();
		addFlightToQuery();

		context.close();
	}

	private static <T> void addRecord(T record){
		objectDAO.save(record);
	}

	private static <T> List getRecords(T objectType){
		List<T> list = objectDAO.list(objectType);

		return list;
	}

	private static <T> void showRecords(T objectType){
		List<T> list = objectDAO.list(objectType);

		for(T a : list){
			System.out.println("List:: " + a);
		}
	}


	public static void addFlightToQuery() throws ParseException{

		List<Rout> routList = getRecords(new Rout());
		int flightNum = 11000;
		double cost;
		for(Rout rout : routList){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = "2017-03-20 17:00:00";
			Date term = null;
			String endDateString = "2017-04-30 17:00:00";
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

				System.out.println(date);

				term = dateFormat.parse(date);
				Flight flight = new Flight(term, flightNumber, rout, cost);

				System.out.println(flight);

				objectDAO.save(flight);
				System.out.println("Airport:: " + flight);

				if(term.after(endDate)){
					break;
				}
			}
		}

	}

	public static void addAirportToQuery(){

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
			System.out.println("Airport:: " + airport);
		}
	}

	public static void addRoutToQuery(){

		List<Airport> list = getRecords(new Airport());

		for(Airport airport: list){
			for(Airport airport2 : list){
				if(airport.getIdairport() != airport2.getIdairport()){

					Rout rout = new Rout(airport, airport2);
					objectDAO.save(rout);
					System.out.println("Airport:: " + rout);
				}
			}
		}

	}

}

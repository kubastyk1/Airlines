package com.jstudio.airlines;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jstudio.airlines.json.mapping.ReserveFlight;
import com.jstudio.airlines.json.mapping.UserId;
import com.jstudio.dao.ObjectDAO;
import com.jstudio.model.Airport;
import com.jstudio.model.Flight;
import com.jstudio.model.Person;
import com.jstudio.model.Reservation;
import com.jstudio.model.Rout;
import com.jstudio.model.Trip;
import com.jstudio.model.User;
import com.jstudio.model.UserRole;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private Trip trip;
	private ObjectDAO objectDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws ParseException {

		ApplicationContext ac = new FileSystemXmlApplicationContext("Airlines/src/main/webapp/WEB-INF/spring/HibernateConfig.xml");
		objectDAO = ac.getBean(ObjectDAO.class);

/*		DBInit dbInit = new DBInit(objectDAO);
		dbInit.initDatabase();*/

		Airport airport = new Airport();
		List<Airport> airportList = objectDAO.list(new Airport());

		model.addAttribute("airportList", airportList);

		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) throws ParseException, InterruptedException {

        Thread.sleep(1000); // simulated delay
        Airport fromAirport = null;
        Airport toAirport = null;
        List<Flight> flightsList = new ArrayList<Flight>();
        List<Rout> routList = null;

        if(!trip.getFromAirport().equals("From: ")){
        	fromAirport = objectDAO.getAirport(trip.getFromAirport());
        }

        if(!trip.getToAirport().equals("To: ")){
        	toAirport = objectDAO.getAirport(trip.getToAirport());
        }

        if(fromAirport == null && toAirport == null){

        	flightsList = objectDAO.list(new Flight());

        } else if(fromAirport != null && toAirport == null){

        	List<Flight> tempFlightsList = null;
        	routList = objectDAO.getRoutWhereFromAirport(fromAirport);
        	for(Rout rout : routList){
        		System.out.println(rout.getIdrout());
        		tempFlightsList = objectDAO.getFlightsWithRout(rout);
        		flightsList.addAll(tempFlightsList);
        	}

        } else if(fromAirport == null && toAirport != null){

        	List<Flight> tempFlightsList = null;
        	routList = objectDAO.getRoutWhereToAirport(toAirport);
        	for(Rout rout : routList){
        		System.out.println(rout.getIdrout());
        		tempFlightsList = objectDAO.getFlightsWithRout(rout);
        		flightsList.addAll(tempFlightsList);
        	}

        } else {

        	Rout rout = objectDAO.getRout(fromAirport, toAirport);
    		flightsList = objectDAO.getFlightsWithRout(rout);
        }

		model.addAttribute("flightsList", flightsList);

		return "search";
	}

	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public String userProfile(@RequestParam("submit") String username, Model model) throws ParseException {

		User user = objectDAO.getUser(username);
		List<Reservation> reservationsList = objectDAO.getReservations(user);

		model.addAttribute("reservationsList", reservationsList);
		return "userProfile";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
				model.addObject("loginUpdate", true);
			}
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}

	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}

	@RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
	public ModelAndView updatePage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addObject("loginUpdate", true);
			model.setViewName("/login");

		} else {
			model.setViewName("update");
		}

		return model;

	}

	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}

	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet() {

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Person person, Model model) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(person.getPassword());

		User user = new User(person.getLogin(), hashedPassword, true);
		UserRole userRole = new UserRole(user, "ROLE_USER");

		objectDAO.save(user);
		objectDAO.save(userRole);

		return "home";
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		List<UserRole> userRoleList = objectDAO.list(new UserRole());

		ModelAndView model = new ModelAndView();
		model.addObject("userRoleList", userRoleList);
		model.setViewName("admin");

		return model;
	}

	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();

	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addObject("username", userDetail.getUsername());
	  }

	  model.setViewName("403");
	  return model;

	}

	@MessageMapping("/sendValues")
    public void showValues(Trip trip) throws Exception {
        Thread.sleep(500); // simulated delay

        this.trip = trip;
    }

	@MessageMapping("/deleteUser")
    public void deleteUser(UserId userId) throws Exception {
        Thread.sleep(3000); // simulated delay

        System.out.println("DDDDDDelete" + userId.getId());

        List<UserRole> userRoleList = objectDAO.list(new UserRole());
        for(UserRole userRole : userRoleList){
        	if(userRole.getUserRoleId() == Integer.parseInt(userId.getId())){
        		 System.out.println("FFFFFlete" + userRole.getUserRoleId());
        		objectDAO.deleteUser(userRole);
        	}
        }
    }

	@MessageMapping("/reserveFlight")
    public void reserveFlight(ReserveFlight reserveFlight) throws Exception {
        Thread.sleep(3000); // simulated delay

        System.out.println("op " + reserveFlight.getClientName() + "  " + reserveFlight.getIdflight());

        User user = objectDAO.getUser(reserveFlight.getClientName());
        Flight flight = objectDAO.getFlight(reserveFlight.getIdflight());
        Reservation reservation = new Reservation(user, flight);
        objectDAO.save(reservation);
    }
}

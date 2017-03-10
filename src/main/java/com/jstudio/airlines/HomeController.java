package com.jstudio.airlines;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jstudio.model.Airport;
import com.jstudio.dao.ObjectDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static ObjectDAO objectDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("HibernateConfig.xml");

		objectDAO = context.getBean(ObjectDAO.class);

		Airport airport = new Airport();
		List<Airport> airportList = getRecords(airport);

		model.addAttribute("airportList", airportList);

		return "home";
	}

	private static <T> List<T> getRecords(T objectType){
		List<T> list = objectDAO.list(objectType);

		for(T a : list){
			System.out.println("List:: " + a);
		}

		return list;
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
	public String signup(/*User user,*/ Model model) {

	/*	model.addAttribute("newUser", user);*/
		return "rooms";
	}

}

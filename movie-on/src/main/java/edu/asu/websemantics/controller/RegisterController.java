package edu.asu.websemantics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.websemantics.dao.UserDAO;
import edu.asu.websemantics.model.User;

@Controller
public class RegisterController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getDetails() {
		ModelAndView model = new ModelAndView();
		model.setViewName("registration");
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		boolean result = userDAO.insertUserRegisterationDetails(user);
		ModelAndView model = new ModelAndView();
		if (result) {
			model.addObject("success", "Registration was successful");
		} else {
			model.addObject("error", "Registration failed");
		}
		model.setViewName("registration");
		return model;
	}

}

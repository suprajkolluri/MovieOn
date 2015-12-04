package edu.asu.websemantics.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.websemantics.common.UserInfo;
import edu.asu.websemantics.model.MovieDetails;
import edu.asu.websemantics.service.MovieOnService;

@Controller
public class ProfileController {

	@Autowired
	private MovieOnService movieOnService;

	private static List<MovieDetails> movieDetails = null;

	@RequestMapping(value = "/profile**", method = RequestMethod.GET)
	public ModelAndView defaultPage() throws IOException {

		ModelAndView model = new ModelAndView();
		String user = UserInfo.getUserName();
		if (movieDetails == null) {
			movieDetails = movieOnService.moviesAndVideos(user);
		}
		model.addObject("movieDetails", movieDetails);
		model.setViewName("profile");
		return model;

	}
}
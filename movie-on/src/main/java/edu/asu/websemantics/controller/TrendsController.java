package edu.asu.websemantics.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.websemantics.model.MovieDetails;
import edu.asu.websemantics.service.IMDBService;

@Controller
public class TrendsController {

	@Autowired
	private IMDBService imdbService;

	private static List<MovieDetails> horrorList;
	private static List<MovieDetails> actionList;
	private static List<MovieDetails> comedyList;
	private static List<MovieDetails> adventureList;
	private static List<MovieDetails> animationList;

	@RequestMapping(value = "/trends", method = RequestMethod.GET)
	public ModelAndView getTrends() throws IOException {
		ModelAndView model = new ModelAndView();
		if (horrorList == null) {
			horrorList = imdbService.getMovieDetails(new ArrayList<String>() {
				{
					add("Horror");
				}
			});
		}
		if (actionList == null) {
			actionList = imdbService.getMovieDetails(new ArrayList<String>() {
				{
					add("Action");
				}
			});
		}
		if (comedyList == null) {
			comedyList = imdbService.getMovieDetails(new ArrayList<String>() {
				{
					add("Comedy");
				}
			});
		}
		if (adventureList == null) {
			adventureList = imdbService.getMovieDetails(new ArrayList<String>() {
				{
					add("Adventure");
				}
			});
		}
		if (animationList == null) {
			animationList = imdbService.getMovieDetails(new ArrayList<String>() {
				{
					add("Animation");
				}
			});
		}
		model.addObject("horrorList", horrorList);
		model.addObject("actionList", actionList);
		model.addObject("animationList", animationList);
		model.addObject("adventureList", adventureList);
		model.addObject("comedyList", comedyList);
		

		model.setViewName("movietrends");
		return model;
	}
}

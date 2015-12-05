package edu.asu.websemantics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.websemantics.model.MovieDetails;

@Service
public class MovieOnService {

	@Autowired
	private FavouriteGenre favouriteGenre;

	@Autowired
	private IMDBService imdbService;

	public List<MovieDetails> moviesAndVideos(String userId) throws IOException {

		List<String> genres = favouriteGenre.calculateFavoriteGenre(userId);

		genres.add("Action");
		List<MovieDetails> li = imdbService.getMovieDetails(genres);
		System.out.println(li.size());
		System.out.println(li);
		return li;

	}
	

}

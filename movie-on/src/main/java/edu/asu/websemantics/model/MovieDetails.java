package edu.asu.websemantics.model;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails {
	private String moviename;
	private List<VideoResult> videoresult = new ArrayList<VideoResult>();

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public List<VideoResult> getVideoresult() {
		return videoresult;
	}

	public void setVideoresult(List<VideoResult> videoresult) {
		this.videoresult = videoresult;
	}

	@Override
	public String toString() {
		return "MovieDetails [moviename=" + moviename + ", videoresult=" + videoresult + "]";
	}

}

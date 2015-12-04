package edu.asu.websemantics.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.websemantics.model.MovieDetails;
import edu.asu.websemantics.model.VideoResult;

@Service
public class IMDBService {
	static String defaultNamespace = "http://www.semanticweb.org/websem/ontologies/2015/10/entertainment#";

	@Autowired
	YoutubeService youtubeService;

	Model userPreferences = null;

	public List<MovieDetails> getMovieDetails(List<String> genres) throws IOException {
		List<MovieDetails> details = null;
		for (String genre : genres) {
			details = calculateTopMovies(genre);
			if (details.size() == 0) {
				continue;
			} else {
				break;
			}
		}
		return details;
	}

	private List<MovieDetails> calculateTopMovies(String genre) throws IOException {

		System.out.println(genre);

		// create model
		createUserModel();

		return fetchTopMovies(userPreferences, genre);

	}

	private List<MovieDetails> fetchTopMovies(Model model, String genre) throws IOException {

		StringBuffer queryStr = new StringBuffer();

		// Establish Prefixes
		// Set default Name space first
		queryStr.append("PREFIX e" + ": <" + defaultNamespace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		queryStr.append("PREFIX xsd" + ": <" + "http://www.w3.org/2001/XMLSchema#" + ">");
		// Now add query

		String queryRequest = "select ?genre ?rating ?title where{?movie e:rating ?rating.?movie e:preferences ?genre.?movie e:title ?title. ?movie e:hasPopularity ?pop.  FILTER regex(?genre, '"
				+ genre + "').  }ORDER BY (xsd:nonNegativeInteger(?pop)) LIMIT 5";
		queryStr.append(queryRequest);
		System.out.println(queryRequest);

		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		int i = 0;
		List<MovieDetails> details = new ArrayList<MovieDetails>();
		try {
			ResultSet response = qexec.execSelect();

			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				RDFNode title = soln.get("?title");

				if (title != null) // && rating!=null)
				{

					List<VideoResult> list = youtubeService.getYoutubeVideos(title.toString());
					MovieDetails detail = new MovieDetails();
					detail.setMoviename(title.toString());
					detail.setVideoresult(list);

					details.add(detail);
				}

			}
		} finally {
			System.out.println("done!!" + i);
			qexec.close();
		}
		return details;

	}

	private void createUserModel() throws IOException {

		userPreferences = ModelFactory.createOntologyModel();
		InputStream rdfInstance = FileManager.get().open("Ontologies/imdb.rdf");
		userPreferences.read(rdfInstance, defaultNamespace);
		rdfInstance.close();
	}
	/*
	 * public static void main(String args[]) throws IOException { IMDBService
	 * obj = new IMDBService(); List<String> genres = new ArrayList<String>();
	 * genres.add("Action"); List <MovieDetails> li
	 * =obj.getMovieDetails(genres); System.out.println(li.size()); }
	 */
}

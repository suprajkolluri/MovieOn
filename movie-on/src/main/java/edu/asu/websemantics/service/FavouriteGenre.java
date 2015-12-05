package edu.asu.websemantics.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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
import org.springframework.stereotype.Service;

@Service
public class FavouriteGenre {

	static String defaultNamespace = "http://www.semanticweb.org/websem/ontologies/2015/10/entertainment#";
	Model userPreferences = null;

	public List<String> calculateFavoriteGenre(String userId) throws IOException {
		System.out.println(userId);

		// create model
		createUserModel();

		// print preferences and genre
		return fetchPreferences(userPreferences, userId);
	}

	private List<String> fetchPreferences(Model model, String userId) {

		StringBuffer queryStr = new StringBuffer();
		double curRating = 0.0;
		List<Double> countRatingList;		
		double avgRating;		
		double freq = 0;
		List<String> favouriteGenres = new ArrayList<String>();

		// Establish Prefixes
		// Set default Name space first
		queryStr.append("PREFIX e" + ": <" + defaultNamespace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");

		// Now add query
		String queryRequest = "select ?genre ?rating ?user where{?person e:userID ?user. ?person e:preferences ?genre . ?person e:rating ?rating.  FILTER (?user = '"
				+ userId + "').}";
		queryStr.append(queryRequest);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try {
			ResultSet response = qexec.execSelect();
			HashMap<String, List<Double>> ht = new HashMap<String, List<Double>>();
			int numberOfRatedMovies = 0;
			while (response.hasNext()) {

				QuerySolution soln = response.nextSolution();
				RDFNode genre = soln.get("?genre");
				RDFNode rating = soln.get("?rating");
				/* RDFNode user = soln.get("?user"); */
				if (genre != null && rating != null) {

					String genres[] = genre.toString().split("\\|");
					curRating = Double.parseDouble(rating.toString());

					for (int i = 0; i < genres.length; i++) {

						numberOfRatedMovies++;
						if (ht.containsKey(genres[i])) {

							countRatingList = ht.get(genres[i]);
							double overallRating = countRatingList.get(0) + curRating;
							double overallCount = countRatingList.get(1) + 1;							
							ht.put(genres[i], new ArrayList<Double>() {

								{
									add(overallRating);
									add(overallCount);
									add(0.0);
								}
							});
						} else {

							countRatingList = new ArrayList<Double>();
							countRatingList.add(curRating);
							countRatingList.add(1.0);
							countRatingList.add(0.0);
							ht.put(genres[i], countRatingList);
						}
					}
				} else
					System.out.println("No Friends found!");
			}
			//calculating impact function
			HashMap<String, Double> hp = new HashMap<String, Double>();
			for (Entry<String, List<Double>> e : ht.entrySet()) {

				double totalRating = e.getValue().get(0);
				double numberOfGenreMovies = e.getValue().get(1);				
				avgRating = totalRating / numberOfGenreMovies;
				freq = (numberOfGenreMovies * 5)/ numberOfRatedMovies;
				System.out.println("numberOfRatedMovies="+numberOfRatedMovies);
				double impactPoints = avgRating + freq;
				System.out.println("genre= "+ e.getKey()+"and impactPoints= "+ impactPoints);
				hp.put(e.getKey(), impactPoints);
				System.out.println("Key= " + e.getKey() + "Value= " + e.getValue());
			}
			System.out.println("here");
			Map<String,Double> result = MapUtil.sortByValue(hp);
			
			for (Entry<String, Double> e : result.entrySet()) {

				favouriteGenres.add(e.getKey());
				System.out.println(e.getKey());
			}
			

		} finally {
			System.out.println("done!!");
			qexec.close();
		}
		
		return favouriteGenres;

	}
		

	public void createUserModel() throws IOException {

		userPreferences = ModelFactory.createOntologyModel();
		InputStream rdfInstance = FileManager.get().open("Ontologies/user.rdf");
		userPreferences.read(rdfInstance, defaultNamespace);
		rdfInstance.close();
	}

	/* main function*/
	  
	  public static void main(String args[]) throws IOException {
	  
	  FavouriteGenre object = new FavouriteGenre();
	  System.out.println("Select a user from 1 to 367:"); 
	  Scanner input = new Scanner(System.in);
	  String userId = input.nextLine();
	  
	  // Calculating user favourite genre String genre =
	  object.calculateFavoriteGenre(userId);
	  
	  
	  }
	 

}

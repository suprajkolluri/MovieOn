package edu.asu.websemantics.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

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
		fetchPreferences(userPreferences, userId);

		return new ArrayList<String>();
	}

	private void fetchPreferences(Model model, String userId) {

		StringBuffer queryStr = new StringBuffer();

		// Establish Prefixes
		// Set default Name space first
		queryStr.append("PREFIX e" + ": <" + defaultNamespace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");

		// Now add query

		String queryRequest = "select ?genre ?rating ?user where{?person e:userID ?user. ?person e:preferences ?genre . ?person e:rating ?rating.  FILTER (?user = '"
				+ userId + "').}";
		queryStr.append(queryRequest);
		// System.out.println(queryRequest);

		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try {
			ResultSet response = qexec.execSelect();
			Hashtable<String, List<Double>> ht = new Hashtable<String, List<Double>>();
			int numberOfRatedMovies = 0;
			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				RDFNode genre = soln.get("?genre");
				RDFNode rating = soln.get("?rating");
				RDFNode user = soln.get("?user");
				if (genre != null && rating != null && user != null) {
					// System.out.println("genres= " + genre.toString() + " and
					// rating= " + rating.toString()
					// + " and user=" + user.toString());
					String genres[] = genre.toString().split("\\|");
					double curRating = Double.parseDouble(rating.toString());
					for (int i = 0; i < genres.length; i++) {
						// System.out.println("genre= " + genres[i] + " and
						// rating= " + rating);
						numberOfRatedMovies++;
						if (ht.containsKey(genres[i])) {
							List<Double> countRatingList = ht.get(genres[i]);
							double overallRating = countRatingList.get(0) + curRating;
							double overallCount = countRatingList.get(1) + 1;
							ht.put(genres[i], new ArrayList<Double>() {

								{
									add(overallRating);
									add(overallCount);
								}
							});
						} else {
							List<Double> countRatingList = new ArrayList<Double>();
							countRatingList.add(curRating);
							countRatingList.add(1.0);
							ht.put(genres[i], countRatingList);
						}
					}

				}

				else
					System.out.println("No Friends found!");

			}
			double sum = 0;
			double count = 0;
			double avgRating;
			double maxImpactValue = 0;
			double totalRating = 0;
			double numberOfMovies = 0;
			String favGenre = null;
			double freq = 0;

			for (Entry<String, List<Double>> e : ht.entrySet()) {

				System.out.println("Key" + e.getKey() + " Value" + e.getValue());
				totalRating = e.getValue().get(0);
				numberOfMovies = e.getValue().get(1);
				sum = sum + totalRating;
				count = count + numberOfMovies;
				avgRating = totalRating / numberOfMovies;
				freq = numberOfMovies / (20 * numberOfRatedMovies);
				if (freq + avgRating > maxImpactValue) {
					maxImpactValue = freq + avgRating;
					favGenre = e.getKey();
				}
				// calculating impact function

			}
			System.out.println("sum= " + sum);
			System.out.println("number of movies= " + numberOfRatedMovies);
			System.out.println("count= " + count);
			System.out.println("max impact function value= " + maxImpactValue);
			System.out.println("favorite genre= " + favGenre);

		} finally {
			System.out.println("done!!");
			qexec.close();
		}

	}

	public void createUserModel() throws IOException {

		userPreferences = ModelFactory.createOntologyModel();
		InputStream rdfInstance = FileManager.get().open("Ontologies/user.rdf");
		userPreferences.read(rdfInstance, defaultNamespace);
		rdfInstance.close();
	}

	/*
	 * public static void main(String args[]) throws IOException {
	 * 
	 * FavouriteGenre object = new FavouriteGenre(); System.out.println(
	 * "Select a user from 1 to 367:"); Scanner input = new Scanner(System.in);
	 * String userId = input.nextLine();
	 * 
	 * // Calculating user favourite genre String genre =
	 * object.calculateFavoriteGenre(userId);
	 * 
	 * }
	 */

}

package edu.asu.websemantics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import edu.asu.websemantics.model.VideoResult;

@Service
public class YoutubeService {
	private static YouTube youtube;

	public List<VideoResult> getYoutubeVideos(String title) throws IOException {

		youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
			}
		}).setApplicationName("MovieOn").build();

		// Prompt the user to enter a query term.
		String queryTerm = title;

		// Define the API request for retrieving search results.
		YouTube.Search.List search = youtube.search().list("id,snippet");

		// Set your developer key from the {{ Google Cloud Console }} for
		// non-authenticated requests. See:
		// {{ https://cloud.google.com/console }}
		String apiKey = "AIzaSyARuCK4dddDDDxFgerYGUmmHdYkZPcio7s";
		search.setKey(apiKey);
		search.setQ(queryTerm);

		// Restrict the search results to only include videos. See:
		// https://developers.google.com/youtube/v3/docs/search/list#type
		search.setType("video");

		// To increase efficiency, only retrieve the fields that the
		// application uses.
		search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
		search.setMaxResults(new Long(5));

		// Call the API and print results.
		SearchListResponse searchResponse = search.execute();
		List<SearchResult> searchResultList = searchResponse.getItems();
		List<VideoResult> youtubeList = new ArrayList<VideoResult>();
		if (searchResultList != null) {
			youtubeList = getDetail(searchResultList.iterator(), queryTerm);

		}
		return youtubeList;
	}

	private List<VideoResult> getDetail(Iterator<SearchResult> iterator, String queryTerm) {
		// TODO Auto-generated method stub
		List<VideoResult> lt = new ArrayList<VideoResult>();
		while (iterator.hasNext()) {

			SearchResult singleVideo = iterator.next();
			ResourceId rId = singleVideo.getId();
			VideoResult vr = new VideoResult();
			// Confirm that the result represents a video. Otherwise, the
			// item will not contain a video ID.
			if (rId.getKind().equals("youtube#video")) {

				vr.setVideoId(rId.getVideoId());
				vr.setTitle(singleVideo.getSnippet().getTitle());

			}
			lt.add(vr);

		}
		return lt;

	}
}

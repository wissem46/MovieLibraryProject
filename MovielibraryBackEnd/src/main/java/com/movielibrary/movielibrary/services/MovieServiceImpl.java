package com.movielibrary.movielibrary.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movielibrary.movielibrary.entities.Movie;

@Service
public class MovieServiceImpl implements MovieService {
	private List<Movie> list;
	private static List<Movie> movies = new ArrayList<Movie>();

	@Override
	public List<Movie> getAllMovies() {
		return moviesJsonToJavaParser();
	}

	@Override
	public Movie getMovieByTitle(String title) {
		System.out.println(title);

		movies = moviesJsonToJavaParser();
		for (Movie m : movies) {
			if (m.getTitle().equals(title)) {
				System.out.println("loool");
				return m;
			}
		}
		return null;
	}

	// Creation of a movie
	@Override
	public Movie createMovie(Movie movie) {
		movie.setId(Movie.count);
		movies = moviesJsonToJavaParser();
		List<Movie> newMoviesList = new ArrayList<Movie>(movies);
		newMoviesList.add(movie);
		movieJavaToJsonParser(newMoviesList);
		return movie;
	}

	// Delete of a movie
	@Override
	public List<Movie> deleteMovieByTitle(String title) {
		movies = moviesJsonToJavaParser();
		

		List<Movie> newMoviesList = new ArrayList<Movie>(movies);
		for (Iterator<Movie> iter = newMoviesList.iterator(); iter.hasNext();) {
			Movie movie = (Movie) iter.next();
			if (movie.getTitle().equals(title))
				iter.remove();
		}
		movieJavaToJsonParser(newMoviesList);
		return newMoviesList;
	}

	// Update of a movie
	@Override
	public List<Movie> updateMovie(Movie newMovie) {
		movies = moviesJsonToJavaParser();
		for (Iterator<Movie> iter = movies.iterator(); iter.hasNext();) {
			Movie oldMovie = (Movie) iter.next();
			System.out.println("Current movie"+oldMovie.getTitle());
			System.out.println("New movie"+newMovie.getTitle());
			
			if (newMovie.getTitle().equals(oldMovie.getTitle())) {
				System.out.println("***********************************");

				movies.set(movies.indexOf(oldMovie), newMovie);
				movieJavaToJsonParser(movies);
			}

		}
		return movies;
	}

	@Override
	public List<Movie> moviesJsonToJavaParser() {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			movies = Arrays
					.asList(objectMapper.readValue(new File("src/main/resources/json/movies.json"), Movie[].class));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;

	}

	@Override
	public void movieJavaToJsonParser(List<Movie> movies) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File("src/main/resources/json/movies.json"), movies);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Movie getMovieByID(Long id) {
		movies = moviesJsonToJavaParser();
		
		return movies.get(id.intValue());
	}

	@Override
	public List<Movie> deleteMovieByID(Long id) {

		movies = moviesJsonToJavaParser();
		List<Movie> newMoviesList = new ArrayList<Movie>(movies);
		for (Iterator<Movie> iter = newMoviesList.iterator(); iter.hasNext();) {
			Movie movie = (Movie) iter.next();
			if (movie.getId().equals(id))
				iter.remove();
		}
		movieJavaToJsonParser(newMoviesList);
		return newMoviesList;

	}

	@Override
	public List<Movie> updateMovieByID(Long id, Movie newMovie) {
		movies = moviesJsonToJavaParser();

		movies.set(id.intValue(), newMovie);
		return movies;
	}

}

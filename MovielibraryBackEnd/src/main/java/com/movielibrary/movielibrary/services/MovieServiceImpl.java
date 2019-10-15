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
import com.movielibrary.movielibrary.util.Utilities;

@Service
public class MovieServiceImpl implements MovieService {

	private final static String jsonFilePath = "src/main/resources/json/movies.json";
	private static List<Movie> movies = new ArrayList<Movie>();

	@Override
	public List<Movie> getAllMovies() {
		return Utilities.moviesJsonToJavaParser(movies, new File(jsonFilePath));
	}

	@Override
	public Movie getMovieByTitle(String title) {

		movies = Utilities.moviesJsonToJavaParser(movies, new File(jsonFilePath));
		for (Movie m : movies) {
			if (m.getTitle().equals(title)) {
				return m;
			}
		}
		return null;
	}

	// Creation of a movie
	@Override
	public Movie createMovie(Movie movie) {
		
		movies = Utilities.moviesJsonToJavaParser(movies, new File(jsonFilePath));
		List<Movie> newMoviesList = new ArrayList<Movie>(movies);
		newMoviesList.add(movie);
		Utilities.movieJavaToJsonParser(newMoviesList, new File(jsonFilePath));
		return movie;
	}

	// Delete of a movie
	@Override
	public List<Movie> deleteMovieByTitle(String title) {
		movies = Utilities.moviesJsonToJavaParser(movies, new File(jsonFilePath));

		List<Movie> newMoviesList = new ArrayList<Movie>(movies);
		for (Iterator<Movie> iter = newMoviesList.iterator(); iter.hasNext();) {
			Movie movie = (Movie) iter.next();
			if (movie.getTitle().equals(title))
				iter.remove();
		}
		Utilities.movieJavaToJsonParser(newMoviesList, new File(jsonFilePath));
		return newMoviesList;
	}

	// Update of a movie
	@Override
	public List<Movie> updateMovie(Movie newMovie) {
		movies = Utilities.moviesJsonToJavaParser(movies, new File(jsonFilePath));
		for (Iterator<Movie> iter = movies.iterator(); iter.hasNext();) {
			Movie oldMovie = (Movie) iter.next();

			if (newMovie.getTitle().equals(oldMovie.getTitle())) {

				movies.set(movies.indexOf(oldMovie), newMovie);
				Utilities.movieJavaToJsonParser(movies, new File(jsonFilePath));
			}

		}
		return movies;
	}

}

package com.movielibrary.movielibrary.services;

import java.io.File;
import java.util.List;

import com.movielibrary.movielibrary.entities.Movie;


public interface MovieService {

	public List<Movie> moviesJsonToJavaParser();
	
	public void movieJavaToJsonParser(List<Movie> movies);
	
	public List<Movie> getAllMovies();
	
	public Movie getMovieByTitle(String title);
	
	public Movie getMovieByID(Long id);
	
	public Movie createMovie(Movie movie);
	
	public List<Movie>  deleteMovieByTitle(String title);
	
	public List<Movie>  deleteMovieByID(Long id);
	
	public List<Movie> updateMovie(Movie movie);
	
	public List<Movie> updateMovieByID(Long id, Movie movie);
	
}

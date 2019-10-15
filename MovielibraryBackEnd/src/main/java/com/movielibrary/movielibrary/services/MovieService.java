package com.movielibrary.movielibrary.services;

import java.io.File;
import java.util.List;

import com.movielibrary.movielibrary.entities.Movie;

public interface MovieService {

	public List<Movie> getAllMovies();

	public Movie getMovieByTitle(String title);

	public Movie createMovie(Movie movie);

	public List<Movie> deleteMovieByTitle(String title);

	public List<Movie> updateMovie(Movie movie);

}

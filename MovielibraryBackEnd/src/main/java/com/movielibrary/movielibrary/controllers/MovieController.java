package com.movielibrary.movielibrary.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movielibrary.movielibrary.entities.Movie;
import com.movielibrary.movielibrary.services.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public List<Movie> getAllMovies(){
		
		return movieService.getAllMovies(); 
	}
	
	@GetMapping("/movies/{title}")
	public ResponseEntity<?> getMovie(@PathVariable("title") String title) {
		System.out.println(title);
	Movie movie = movieService.getMovieByTitle(title);
	if (movie == null) {
	return new ResponseEntity<String>("No Movie found with title " + title, HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	@PostMapping("/movies")
	public Movie createMovie(@RequestBody Movie movie) {
		System.out.println(movie.getTitle());

		//return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
	return movieService.createMovie(movie);
	}
	
	@DeleteMapping("/movies/{title}")
	public ResponseEntity<?> removeMovieByTitle(@PathVariable("title") String title)
	{	
		System.out.println("delete controller");

		movieService.deleteMovieByTitle(title);
		//return movieService.getAllMovies();
		return new ResponseEntity<>("Movie is deleted successfully", HttpStatus.OK);

	}
	

	@PutMapping("/movies")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie){

		movieService.updateMovie(movie);
	//	return movieService.getAllMovies();
		return new ResponseEntity<>("Movie is updated successfully", HttpStatus.OK);
		
	}
	
}

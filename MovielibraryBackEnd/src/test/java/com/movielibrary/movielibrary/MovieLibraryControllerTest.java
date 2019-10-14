package com.movielibrary.movielibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.movielibrary.movielibrary.entities.Movie;

public class MovieLibraryControllerTest extends AbstractMovieLibraryApplicationTest{
	
	
	@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	
	
	
	   @Test
	   public void getAllMovies() throws Exception {
	      String uri = "/movies";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      Movie[] movieslist = super.mapFromJson(content, Movie[].class);
	      assertTrue(movieslist.length > 0);
	   }
	   @Test
	   public void createMovie() throws Exception {
	      String uri = "/movies";
	      Movie movie = new Movie();
	      movie.setTitle("Scream");
	      movie.setDirector("Hugo agogo");
	      movie.setReleaseDate(LocalDate.now());
	      movie.setType("sci");
	      String inputJson = super.mapToJson(movie);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Movie is created successfully");
	   }
	   @Test
	   public void updateMovie() throws Exception {
	      String uri = "/movies";
	      Movie movie = new Movie();
	      movie.setTitle("Joker");
	      movie.setDirector("New Director");
	      movie.setReleaseDate(LocalDate.now());
	      movie.setType("sci");
	      String inputJson = super.mapToJson(movie);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Movie is updated successfully");
	   }
	   @Test
	   public void removeMovieByTitle() throws Exception {
	      String uri = "/movies/Avengers: Infinity War";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Movie is deleted successfully");
	   }
	}



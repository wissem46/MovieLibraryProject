package com.movielibrary.movielibrary.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movielibrary.movielibrary.entities.Movie;

public class Utilities {

	public static List<Movie> moviesJsonToJavaParser(List<Movie> movies, File file) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			movies = Arrays.asList(objectMapper.readValue(file, Movie[].class));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;

	}

	public static void movieJavaToJsonParser(List<Movie> movies, File file) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(file, movies);
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
}

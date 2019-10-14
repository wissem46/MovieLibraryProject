import { MovieDetailsComponent } from '../movie-details/movie-details.component';
import { Observable } from "rxjs";
import { MovieService } from "../movie.service";
import { Movie } from "../movie";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-movie-list",
  templateUrl: "./movie-list.component.html",
  styleUrls: ["./movie-list.component.css"]
})
export class MovieListComponent implements OnInit {
  movies: Observable<Movie[]>;
  searchText: string = "";

  constructor(private movieService: MovieService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    //this.movies = this.movieService.getMoviesList();
    this.movieService.getMoviesList().subscribe(data=>{
      this.movies=data;
    });
  }

  updateMovie(title: string) {
    this.router.navigate(['update', title]);
  }
  deleteMovie(title: string) {
    if(confirm('Are you sure to delete this movie ?')){

    
    this.movieService.deleteMovie(title)
      .subscribe(
        data => {

          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
      }
  }

  movieDetails(title: string) {
    this.router.navigate(['details', title]);
  }

}
import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrls: ['./update-movie.component.css']
})
export class UpdateMovieComponent implements OnInit {

  id: number;
  movie: Movie;
  title:string;

  constructor(private route: ActivatedRoute,private router: Router,
    private movieService: MovieService) { }

  ngOnInit() {
    this.movie = new Movie();

    this.title = this.route.snapshot.params['title'];
    
    this.movieService.getMovie(this.title)
      .subscribe(data => {
        console.log(data)
        this.movie = data;
      }, error => console.log(error));
  }

  updateMovie() {
    this.movieService.updateMovie(this.title, this.movie)
      .subscribe(data => console.log(data), error => console.log(error));
    this.movie = new Movie();
    this.gotoList();
  }

  onSubmit() {
    this.updateMovie();    
  }

  gotoList() {
    this.router.navigate(['/movies']);
  }
}
import { Movie } from '../movie';
import { Component, OnInit, Input } from '@angular/core';
import { MovieService } from '../movie.service';
import { MovieListComponent } from '../movie-list/movie-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

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

  list(){
    this.router.navigate(['movies']);
  }
}
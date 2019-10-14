import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private baseUrl = 'http://localhost:8080/movies';

  constructor(private http: HttpClient) { }

// tested OK
  getMoviesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  //tested OK
  getMovie(title: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${title}`);
  }
//tested Ok
  createMovie(movie: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, movie);
  }
//tested Ok
  deleteMovie(title: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${title}`, { responseType: 'text' });
  }

  updateMovie(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}`, value);
  }

  
 
}
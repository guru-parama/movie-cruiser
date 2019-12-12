import { Injectable } from '@angular/core';
import { Movie } from './movie-info/movie';
import { Genre } from './movie-info/genre';
import { AuthService } from '../site/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from '../site/authenticate.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  movieList: Movie[];
  movieListCustomer: Movie[];
  filterdMovieList: Movie[];
  genre: Genre[];
  baserUrl = environment.baseUrl;

  constructor(private httpClient: HttpClient
      , private authenticateService: AuthenticateService) {
        
  }

  getAllMovies(){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.get<Movie[]>('http://localhost:8086/movie-service/moviecruiser/movies',httpOption);
  }

  getMovie(id){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.get<Movie>('http://localhost:8086/movie-service/moviecruiser/movies'+id,httpOption)
  }

  updateMovie(edited: Movie){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.put('http://localhost:8086/movie-service/moviecruiser/movies',edited, httpOption);
  }

  getGenreList(){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.get<Genre[]>('http://localhost:8086/movie-service/moviecruiser/genre', httpOption);
  }

}

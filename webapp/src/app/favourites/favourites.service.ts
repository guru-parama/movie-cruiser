import { Injectable } from '@angular/core';
import { Favourite } from './favourites-list/favourites';
import { Movie } from '../movie/movie-info/movie';
import { MovieService } from '../movie/movie.service';
import { AuthenticateService } from '../site/authenticate.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {

  favouriteMovieList: Favourite;
  movieList: Movie[];

  constructor(private movieService: MovieService,
       private authenticateService: AuthenticateService,
       private httpClient: HttpClient) { 

    //this.movieList = movieService.getAllMovies();

  }

  getAllFavourites(userId){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.get<Favourite>("http://localhost:8086/movie-service/moviecruiser/favourites/"+userId, httpOption);
  }

  addFavouriteMovie(userId,id){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.post<boolean>("http://localhost:8086/movie-service/moviecruiser/favourites/"+userId+"/"+id,{},httpOption);
  }

  removeFavouriteMovie(userId,id){
    let token = "Bearer "+ this.authenticateService.getToken();
    const httpOption = { headers : new HttpHeaders({'Content-Type' : 'application/json', 'Authorization': token})};
    return this.httpClient.delete("http://localhost:8086/movie-service/moviecruiser/favourites/"+userId+"/"+id, httpOption);
  }
}

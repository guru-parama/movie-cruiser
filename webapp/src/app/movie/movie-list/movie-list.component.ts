import { Component, OnInit } from '@angular/core';
import { MovieService } from '../movie.service';
import { Movie } from '../movie-info/movie';
import { FavouritesService } from 'src/app/favourites/favourites.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/site/auth.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movieList: Movie[];
  filteredList: Movie[];
  addToFavId: number;
  userName: String;
  alreadyExistingFlag: boolean;

  constructor(private movieService: MovieService
    , private favService: FavouritesService
    , private userAuth: AuthService) {
    this.userName = userAuth.getRole();
   }

  ngOnInit() {
    this.movieService.getAllMovies().subscribe(responce=>{
      this.movieList = responce;
      this.filteredList = this.movieList;      
    })
  }

  fillterData($event){
    this.filteredList = this.movieList.filter((movie: Movie) => movie
                      .name
                      .toLocaleLowerCase()
                      .indexOf($event) != -1
    );
  }

  addFavourite($event){
    this.favService.addFavouriteMovie(this.userAuth.getUserName(),$event).subscribe(responce=>{
      this.alreadyExistingFlag = responce;
    this.addToFavId =  $event;
    });
  }
}

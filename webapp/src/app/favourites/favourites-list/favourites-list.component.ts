import { Component, OnInit } from '@angular/core';
import { FavouritesService } from '../favourites.service';
import { Movie } from 'src/app/movie/movie-info/movie';
import { AuthService } from 'src/app/site/auth.service';

@Component({
  selector: 'app-favourites-list',
  templateUrl: './favourites-list.component.html',
  styleUrls: ['./favourites-list.component.css']
})
export class FavouritesListComponent implements OnInit {

  favouritesList: Movie[];
  removeFlag: boolean;
  favouriteEmplyFlag: boolean = false;
  total: number;

  constructor(private favService: FavouritesService, private userAuth: AuthService ) {
    favService.getAllFavourites(userAuth.getUserName()).subscribe(responce=>{
      if(responce != null){
      this.favouritesList = responce.movieList;
      this.total = responce.total;
      }
    },error=>{
      console.log(error.error.message);
      this.favouriteEmplyFlag = true;
    });
    this.removeFlag = false;
   }

  ngOnInit() {
  }

  remove(id,index){
    this.favService.removeFavouriteMovie(this.userAuth.getUserName(),id).subscribe(responce=>{
      this.favouritesList.splice(index,1);
      this.favService.getAllFavourites(this.userAuth.getUserName()).subscribe(responce=>{
        if(responce != null){
        this.favouritesList = responce.movieList;
        this.total = responce.total;
        this.removeFlag = true;
        }
      },error=>{
        console.log(error.error.message);
        this.favouriteEmplyFlag = true;
      });
    }
    );
  }

}

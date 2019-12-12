import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/site/auth.service';
import { FavouritesService } from 'src/app/favourites/favourites.service';

@Component({
  selector: 'app-movie-info',
  templateUrl: './movie-info.component.html',
  styleUrls: ['./movie-info.component.css']
})
export class MovieInfoComponent implements OnInit {

  @Input() movieDetails: any;
  @Output() addFavouriteEmitter: any;
  @Input() addMovieId: any;
  @Input() userName: any;
  @Input() movieInFav: any;

  constructor(private router: Router
    , private userAuth: AuthService
    , private favouriteService: FavouritesService) { 
    this.addFavouriteEmitter = new EventEmitter();
    this.userName = userAuth.getUserName();
  }

  ngOnInit() {
  }

  add(id){
    if(this.userName == null){
      this.userAuth.setTempFavId(id);
      this.router.navigate(["login",'null']);
    }
    else
      this.addFavouriteEmitter.emit(id);
  }

  edit(id){
    this.router.navigate(['movie-edit', id]);
  }
}

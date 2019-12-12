import { Injectable } from '@angular/core';
import { User } from './user';
import { AuthenticateService } from './authenticate.service';
import { FavouritesService } from '../favourites/favourites.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  loggedIn: boolean;
  name: String = null;
  role: String = null;
  tempFavId: number = null;
  constructor(private authenticateService: AuthenticateService) {
    this.loggedIn = false;
   }

  login(){
    this.loggedIn= true;
    if(this.name == 'admin'){
      this.tempFavId = null;
    }
  }
  setUserName(userInput: String){
    this.name = userInput;
  }
  getUserName(){
    return this.name;
  }
  setRole(role){
    this.role = role;
  }
  getRole(){
    return this.role;
  }
  setTempFavId(id){
    this.tempFavId = id
  }
  getTempFavIg(){
    return this.tempFavId;
  }
  logout(){
    this.name = null;
    this.tempFavId = null;
    this.loggedIn= false;
    this.role = null;
    this.authenticateService.setToken(null);
  }
  isLoggedin(){
    return this.loggedIn;
  }

}

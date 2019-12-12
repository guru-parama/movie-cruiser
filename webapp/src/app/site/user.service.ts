import { Injectable } from '@angular/core';
import { User } from './user';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  user: User[];
  loginFlag: boolean;

  constructor(private userAuth: AuthService
      , private router: Router
      , private httpClient: HttpClient) {
  }

  addUser(newUser: User){
    return this.httpClient.post<User>('http://localhost:8086/userauth-service/signup', newUser);
  }
  
  getUser(){
    return this.user;
  }

}

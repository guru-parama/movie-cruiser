import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { AuthenticateService } from '../authenticate.service';
import { FavouritesService } from 'src/app/favourites/favourites.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginFlag: boolean;
  user: User[];
  favouriteFlag: boolean;

  constructor(private userService: UserService
    , private router: Router
    , private userAuth: AuthService
    , private activeRoute: ActivatedRoute
    , private favouriteService: FavouritesService
    , private authenticateService: AuthenticateService) {
    this.user = userService.getUser();
   }

  ngOnInit() {
    this.loginForm = new FormGroup ({
      userName: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required])
    });

    if(this.activeRoute.snapshot.paramMap.get('userName') == 'null'){
      this.favouriteFlag = true;
    }
  }

  login(){
    this.authenticateService.authenticate(this.loginForm.value.userName, this.loginForm.value.password)
      .subscribe(responce => {
        this.userAuth.setUserName(this.loginForm.value.userName);
        this.authenticateService.setToken(responce.token);
        this.userAuth.login();        
        this.userAuth.setRole(responce.role);
        if(this.userAuth.getTempFavIg() != null){
          this.favouriteService.addFavouriteMovie(this.loginForm.value.userName,this.userAuth.getTempFavIg()).subscribe();
        }
        this.router.navigate(['movie-list',this.loginForm.value.userName]);
        this.loginFlag = this.userService.loginFlag;
    
      })
  
  }
}

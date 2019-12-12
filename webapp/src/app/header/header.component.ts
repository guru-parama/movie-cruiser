import { Component, OnInit } from '@angular/core';
import { AuthService } from '../site/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  name: String;
  constructor(private authService: AuthService, private router: Router) {
   }

  ngOnInit() {
  }

  getUsername(){
    return this.authService.getUserName();
  }
  getName(){
    this.name = this.authService.getRole();
    return this.name;
  }

  signout(){
    this.authService.logout();
    if(this.name == null)
    this.router.navigate(['']);
  }
}
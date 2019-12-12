import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MovieService } from 'src/app/movie/movie.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  userForm: FormGroup;
  passwordFlag: boolean;
  newUser: User = {
    userName: "",
    firstName: "",
    lastName: "",
    password: ""
  };
  userFlag: boolean = false;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {

    this.userForm = new FormGroup({
      userName: new FormControl('',[Validators.required]),
      firstName: new FormControl('',[Validators.required]),
      lastName: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required]),
      confirmPassword : new FormControl('',[Validators.required])
    })

    
}

  passMatch(){
    if(this.userForm.value.password == this.userForm.value.confirmPassword ){
      this.passwordFlag = true;
    }
    else
      this.passwordFlag = false;
  }

  signup(){
    this.newUser.userName = this.userForm.value.userName;
    this.newUser.firstName = this.userForm.value.firstName;
    this.newUser.lastName = this.userForm.value.lastName;
    this.newUser.password = this.userForm.value.password;
    this.userService.addUser(this.newUser).subscribe(responce=>{
    this.router.navigate(['login']);
  },
    error=>{
      if(error.status==406)
        this.userFlag = true;
    });
  }
}
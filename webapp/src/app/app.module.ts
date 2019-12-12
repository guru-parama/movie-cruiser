import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MovieInfoComponent } from './movie/movie-info/movie-info.component';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { MovieSearchComponent } from './movie/movie-search/movie-search.component';
import { FavouritesListComponent } from './favourites/favourites-list/favourites-list.component';
import { MovieEditComponent } from './movie/movie-edit/movie-edit.component';
import { DatePipe } from '@angular/common';
import { SignupComponent } from './site/signup/signup.component';
import { LoginComponent } from './site/login/login.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MovieInfoComponent,
    MovieListComponent,
    MovieSearchComponent,
    FavouritesListComponent,
    MovieEditComponent,
    SignupComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BsDatepickerModule.forRoot(),
    HttpClientModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

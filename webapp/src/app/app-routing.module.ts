import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { FavouritesListComponent } from './favourites/favourites-list/favourites-list.component';
import { MovieEditComponent } from './movie/movie-edit/movie-edit.component';
import { SignupComponent } from './site/signup/signup.component';
import { LoginComponent } from './site/login/login.component';
import { AuthGuard } from './site/auth.guard';

const routes: Routes = [
  {path:'', component: MovieListComponent},
  {path:'movie-list/:userName', component: MovieListComponent, canActivate: [AuthGuard]},
  {path:'movie-list', component: MovieListComponent},
  {path:'favourites-list', component: FavouritesListComponent, canActivate: [AuthGuard]},
  {path:'movie-edit/:id', component: MovieEditComponent, canActivate: [AuthGuard]},
  {path:'movie-edit', component: MovieEditComponent, canActivate: [AuthGuard]},
  {path:'signup', component: SignupComponent},
  {path:'login/:userName', component: LoginComponent},
  {path:'login', component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

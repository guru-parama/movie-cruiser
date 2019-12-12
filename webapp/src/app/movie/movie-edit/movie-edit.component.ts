import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../movie.service';
import { Movie } from '../movie-info/movie';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Genre } from '../movie-info/genre';

@Component({
  selector: 'app-movie-edit',
  templateUrl: './movie-edit.component.html',
  styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

  movieList: Movie[];
  movie ={
    id: null,
    name: "",
    boxOffice: null,
    active: null,
    dateOfLaunch: new Date(),
    genre: null,
    hasTeaser: null,
    image: ""
  };
  movieForm: FormGroup;
  movieId: number;
  genre: Genre[];
  movieEdited: Movie;
  editFlag: boolean = false;

  constructor(private router: ActivatedRoute, private movieService: MovieService, private datePipe: DatePipe) { 
    //this.movieList = movieService.getAllMovies();
    movieService.getAllMovies().subscribe(responce=>{
      this.movieList = responce;
      movieService.getGenreList().subscribe(responce => {
        console.log(responce);
        this.genre = responce;
      });
    })
  }

  ngOnInit() {

    this.movieForm = new FormGroup({
      name: new FormControl(this.movie.name,[Validators.required]),
      boxOffice : new FormControl(this.movie.boxOffice,[Validators.required, Validators.pattern('[0-9]+')]),
      dateOfLaunch: new FormControl(this.movie.dateOfLaunch,[Validators.required]),
      division: new FormGroup({
        genre : new FormControl(this.movie.genre)
      }),
      active : new FormControl(this.movie.active),
      hasTeaser : new FormControl(this.movie.hasTeaser)
    })

    this.movieService.getMovie(this.router.snapshot.params['id']).subscribe(responce=>{
      this.movie = responce;
      this.movieForm.setValue({
        name: this.movie.name,
        active: this.movie.active,
        boxOffice: this.movie.boxOffice,
        dateOfLaunch: new Date(this.movie.dateOfLaunch),
        division:{
          genre: this.movie.genre
        },
        hasTeaser: this.movie.hasTeaser
      })
    });
  }

  send(){

    this.movieEdited = {
    id : this.movie.id,
    name : this.movieForm.value.name,
    boxOffice : this.movieForm.value.boxOffice,
    dateOfLaunch : new Date(this.movieForm.value.dateOfLaunch),
    genre : this.movieForm.value.division.genre,
    active : this.movieForm.value.active,
    hasTeaser : this.movieForm.value.hasTeaser,
    image : this.movie.image
    }

    this.movieService.updateMovie(this.movieEdited).subscribe();
    this.editFlag = true;

  }

}

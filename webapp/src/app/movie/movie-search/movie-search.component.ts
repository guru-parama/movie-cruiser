import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.css']
})
export class MovieSearchComponent implements OnInit {

  @Output() searchEmitter: any;

  searchString: String;

  constructor() { 
    this.searchEmitter = new EventEmitter();
  }

  ngOnInit() {
  }

  filter(){
    this.searchEmitter.emit(this.searchString);
  }
}

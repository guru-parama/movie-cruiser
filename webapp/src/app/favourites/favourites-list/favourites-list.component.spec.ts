import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouritesListComponent } from './favourites-list.component';

describe('FavouritesListComponent', () => {
  let component: FavouritesListComponent;
  let fixture: ComponentFixture<FavouritesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FavouritesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FavouritesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

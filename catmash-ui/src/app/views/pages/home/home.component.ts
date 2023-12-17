import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cat } from 'src/app/model/cat';
import { CatService } from 'src/app/services/cat-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit  {


  cats: Array<Cat> = [];

  constructor(private catService : CatService, private router: Router) { }

  ngOnInit(): void {
    this.getAllCatsWithScore();
  }

  getAllCatsWithScore(){
    this.catService
    .getAllCatsWithScore()
    .subscribe((response: any) => {
          this.cats = response;
    });
  }

  voteForCat(id: string){
    console.log("Cat to be modified " + id);
    this.catService.voteForCat(id)
    .subscribe((response: Cat) => {
      console.log('Updated cat ' + response);
    });
    window.location.reload();
  }
}

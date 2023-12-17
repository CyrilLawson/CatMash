import { Component, OnInit } from '@angular/core';
import { Cat } from 'src/app/model/cat';
import { CatService } from 'src/app/services/cat-service.service';

@Component({
  selector: 'app-cutest-cat',
  templateUrl: './cutest-cat.component.html',
  styleUrls: ['./cutest-cat.component.scss']
})
export class CutestCatComponent implements OnInit {

  constructor(private catService : CatService) { }

  cats : Array<Cat> = [];

  ngOnInit(): void {
    this.getCutestCats();
  }

  getCutestCats() {
    this.catService
    .getTop5CutestCats()
    .subscribe((response: Array<Cat>) => {
          this.cats = response;
    });
  }

}

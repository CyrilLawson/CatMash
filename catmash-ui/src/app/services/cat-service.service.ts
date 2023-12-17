import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cat } from '../model/cat';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CatService {

  constructor(private http: HttpClient) {
  }

  voteForCat(id : string): Observable<Cat> {
    return this.http.put<Cat>('http://localhost:8081/api/v1/cats/' + id, id);
  }

  getTop5CutestCats(): Observable<Array<Cat>> {
    return this.http.get<Array<Cat>>('http://localhost:8081/api/v1/cats');
  }

  getAllCatsWithScore(): Observable<Array<Cat>> {
    return this.http.get<Array<Cat>>('http://localhost:8081/api/v1/cats/all');
  }
}

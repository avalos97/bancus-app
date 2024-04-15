import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GenericResponse } from 'src/app/shared/interface/generic-response';
import { environment } from 'src/environments/environment';
import { Joke } from '../interface/joke';

@Injectable({
  providedIn: 'root'
})
export class JokeService {

  apiUrl = environment.REST_API_URL + 'chuck-norris-client';

  constructor(private http: HttpClient) { }

  getPublicity(): Observable<GenericResponse<Joke>> {
    return this.http.get<GenericResponse<Joke>>(this.apiUrl);
  }

}

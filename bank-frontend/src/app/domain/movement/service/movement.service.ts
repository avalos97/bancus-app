import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movement } from '../interface/movement';
import { GenericResponse } from 'src/app/shared/interface/generic-response';

@Injectable({
  providedIn: 'root'
})
export class MovementService {

  apiUrl = environment.REST_API_URL + 'movement';

  constructor(private http: HttpClient) { }

  getAllMovements(): Observable<GenericResponse<Movement[]>> {
    return this.http.get<GenericResponse<Movement[]>>(this.apiUrl);
  }

  getMovementsByCriteria(customerId:number, startDate:Date, endDate:Date): Observable<GenericResponse<Movement[]>> {
    let searchParams = new HttpParams();
    searchParams = searchParams.append("customerId", customerId.toString());
    searchParams = searchParams.append("startDate", startDate.toISOString().split('T')[0]);
    searchParams = searchParams.append("endDate", endDate.toISOString().split('T')[0]);
    
    return this.http.get<GenericResponse<Movement[]>>(`${this.apiUrl}/by-criteria`, { params: searchParams });
  }

  createMovement(movement: Movement): Observable<GenericResponse<Movement>> {
    return this.http.post<GenericResponse<Movement>>(this.apiUrl, movement);
  }

  updateMovement(id:number, movement: Movement): Observable<GenericResponse<Movement>> {
    return this.http.put<GenericResponse<Movement>>(`${this.apiUrl}/${id}`, movement);
  }

  deleteMovement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

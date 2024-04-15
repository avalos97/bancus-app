import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GenericResponse } from 'src/app/shared/interface/generic-response';
import { Customer } from '../interface/customer';
import { Account } from '../interface/account';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  apiUrl = environment.REST_API_URL + 'customer';

  constructor(private http: HttpClient) { }

  getAllCustomer(): Observable<GenericResponse<Customer[]>> {
    return this.http.get<GenericResponse<Customer[]>>(this.apiUrl);
  }

  getCustomerById(id: number): Observable<GenericResponse<Customer>> {
    return this.http.get<GenericResponse<Customer>>(`${this.apiUrl}/${id}`);
  }

  createCustomer(customer: Customer): Observable<GenericResponse<Customer>> {
    return this.http.post<GenericResponse<Customer>>(this.apiUrl, customer);
  }

  updateCustomer(id:number, customer: Customer): Observable<GenericResponse<Customer>> {
    return this.http.put<GenericResponse<Customer>>(`${this.apiUrl}/${id}`, customer);
  }

  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  saveCustomerAccount(id:number, account: Account): Observable<GenericResponse<Account[]>> {
    return this.http.post<GenericResponse<Account[]>>(`${this.apiUrl}/${id}/account`, account);
  }

  updateCustomerAccount(id:number, idAccount:number, account: Account): Observable<GenericResponse<Account[]>> {
    return this.http.put<GenericResponse<Account[]>>(`${this.apiUrl}/${id}/account/${idAccount}`, account);
  }

  deleteCustomerAccount(id:number, idAccount:number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}/account/${idAccount}`);
  }
}

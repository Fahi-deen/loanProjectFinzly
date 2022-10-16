import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private baseUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) {}
  createLoan(data: any) {
    return this.http.post(`${this.baseUrl}createloan`, data);
  }
  getAllLoans() {
    return this.http.get(`${this.baseUrl}allLoans`);
  }
  currentCustomerPaymentDetails(id: any) {
    return this.http.get(`${this.baseUrl}current/${id}`);
  }

  updatePaymentStatus(payment: any) {
    console.log(payment);

    return this.http.put(`${this.baseUrl}updatePaymentStatus`, payment);
  }
}

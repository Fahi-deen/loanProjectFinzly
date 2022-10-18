import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private baseUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) {}
  createLoan(data: any) {
    console.log(data);

    return this.http.post(`${this.baseUrl}createloan`, data);
  }
  getAllLoans() {
    return this.http.get(`${this.baseUrl}allLoans`);
  }
  currentCustomerPaymentDetails(id: any) {
    return this.http.get(`${this.baseUrl}currentUserPayments/${id}`);
  }

  updatePaymentStatus(id: any) {
    return this.http.put(`${this.baseUrl}updatePaymentStatus/${id}`, '');
  }
}

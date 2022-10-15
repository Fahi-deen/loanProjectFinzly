import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-displayAllLoans',
  templateUrl: './displayAllLoans.component.html',
  styleUrls: ['./displayAllLoans.component.css'],
})
export class DisplayAllLoansComponent implements OnInit {
  constructor(
    private service: CustomerService,

    private router: Router
  ) {}
  allLoanData: any;
  currentPaymentScheduleData: any = [];
  p: number = 1;
  ngOnInit() {
    this.onDisplayDetails();
  }
  onDisplayDetails() {
    this.service.getAllLoans().subscribe((data: any) => {
      this.allLoanData = data;
      console.log(this.allLoanData);
    });
  }
  currentPaymentDetails(item: any) {
    console.log(item);
    // this.service.checkCurrentDate(item.customerID);

    this.router.navigate(['/current', item.customerID]);
  }

  onHomePage() {
    this.router.navigate(['dashboard']);
  }
}

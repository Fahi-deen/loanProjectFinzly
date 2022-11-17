import { style } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { ToastrService } from 'ngx-toastr';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-createLoan',
  templateUrl: './createLoan.component.html',
  styleUrls: ['./createLoan.component.css'],
})
export class CreateLoanComponent implements OnInit {
  formdata: any = {};
  date = new Date();

  constructor(
    private router: Router,
    private service: CustomerService,
    private toastr: ToastrService,
    private datePipe: DatePipe
  ) {}
  getToday(): string {
    return new Date().toISOString().split('T')[0];
  }
  ngOnInit() {
    console.log(new Date(new Date().setMonth(new Date().getMonth() + 12)));

    // document.body.style.backgroundColor = '#87CEFA';
    document.body.className = 'sandal_bg';
  }
  disableDate() {
    return false;
  }
  submit() {
    this.formdata.customerName = this.formdata.customerName.trim();

    console.log(this.formdata.loanStartDate);

    console.log(this.formdata.maturityDate);

    this.service.createLoan(this.formdata).subscribe((data) => {
      this.toastr.success('Loan Created Sucessfully', '', {
        progressBar: true,
        closeButton: true,
        positionClass: 'toast-top-right',
      });
      this.router.navigate(['dashboard']);
    });
  }
}

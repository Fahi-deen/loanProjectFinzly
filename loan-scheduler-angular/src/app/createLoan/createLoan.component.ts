import { style } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-createLoan',
  templateUrl: './createLoan.component.html',
  styleUrls: ['./createLoan.component.css'],
})
export class CreateLoanComponent implements OnInit {
  formdata: any = {};

  constructor(
    private router: Router,
    private service: CustomerService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    // document.body.style.backgroundColor = '#87CEFA';
    document.body.className = 'sandal_bg';
  }
  submit() {
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

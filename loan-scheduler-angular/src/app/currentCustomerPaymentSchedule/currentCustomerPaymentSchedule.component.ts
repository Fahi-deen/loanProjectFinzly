import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import Swal from 'sweetalert2';

import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-currentCustomerPaymentSchedule',
  templateUrl: './currentCustomerPaymentSchedule.component.html',
  styleUrls: ['./currentCustomerPaymentSchedule.component.css'],
})
export class CurrentCustomerPaymentScheduleComponent implements OnInit {
  currentCustomerPaymentData: any = [];

  p: number = 1;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: CustomerService
  ) {}

  ngOnInit() {
    this.displayCurrentDetails();
  }
  displayCurrentDetails() {
    this.route.paramMap.subscribe((param: ParamMap) => {
      this.service
        .currentCustomerPaymentDetails(param.get('id'))
        .subscribe((res: any) => {
          console.log(res);

          this.currentCustomerPaymentData = res;
        });
    });
  }
  onConfirmPaid(item: any) {
    Swal.fire({
      title: 'Are you sure?',
      text: `Your are paying Rs ${item.paymentAmount.toFixed(2)}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Pay!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.updatePaymentStatus(item).subscribe((res) => {
          console.log(res);
        });
        Swal.fire('Sucess!', 'Your Payment is Accepted.', 'success').then(
          (): any => {
            window.location.reload();
          }
        );
      }
    });
  }

  onGoBack() {
    this.router.navigate(['loans']);
  }
}

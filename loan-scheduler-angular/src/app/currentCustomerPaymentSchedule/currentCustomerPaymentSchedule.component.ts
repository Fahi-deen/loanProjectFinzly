import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

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
    this.service.updatePaymentStatus(item).subscribe((res) => {
      console.log(res);
    });
    window.location.reload();
  }

  onGoBack() {
    this.router.navigate(['loans']);
  }
}

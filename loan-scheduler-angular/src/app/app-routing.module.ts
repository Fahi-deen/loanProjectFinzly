import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateLoanComponent } from './createLoan/createLoan.component';
import { CurrentCustomerPaymentScheduleComponent } from './currentCustomerPaymentSchedule/currentCustomerPaymentSchedule.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DisplayAllLoansComponent } from './displayAllLoans/displayAllLoans.component';

import { PageNotFoundComponent } from './pageNotFound/pageNotFound.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'createloan', component: CreateLoanComponent },
  { path: 'loans', component: DisplayAllLoansComponent },
  { path: 'current/:id', component: CurrentCustomerPaymentScheduleComponent },

  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

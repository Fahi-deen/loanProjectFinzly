import { NgModule, EventEmitter } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DisplayAllLoansComponent } from './displayAllLoans/displayAllLoans.component';
import { ToastrModule } from 'ngx-toastr';
import { PageNotFoundComponent } from './pageNotFound/pageNotFound.component';
import { CreateLoanComponent } from './createLoan/createLoan.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CurrentCustomerPaymentScheduleComponent } from './currentCustomerPaymentSchedule/currentCustomerPaymentSchedule.component';
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DisplayAllLoansComponent,
    PageNotFoundComponent,
    CreateLoanComponent,
    CurrentCustomerPaymentScheduleComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    NgxPaginationModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
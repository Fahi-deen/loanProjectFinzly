import { NgModule, EventEmitter } from '@angular/core';
import { DatePipe } from '@angular/common';
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
import { Ng2SearchPipeModule } from 'ng2-search-filter';
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
    Ng2SearchPipeModule,
    ToastrModule.forRoot(),
    NgxPaginationModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}

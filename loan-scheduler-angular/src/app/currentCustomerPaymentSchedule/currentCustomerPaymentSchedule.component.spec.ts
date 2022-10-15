/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { CurrentCustomerPaymentScheduleComponent } from './currentCustomerPaymentSchedule.component';

describe('CurrentCustomerPaymentScheduleComponent', () => {
  let component: CurrentCustomerPaymentScheduleComponent;
  let fixture: ComponentFixture<CurrentCustomerPaymentScheduleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentCustomerPaymentScheduleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentCustomerPaymentScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

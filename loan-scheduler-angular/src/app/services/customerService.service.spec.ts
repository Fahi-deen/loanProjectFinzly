/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';

describe('Service: CustomerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CustomerServiceService],
    });
  });

  it('should ...', inject(
    [CustomerServiceService],
    (service: CustomerServiceService) => {
      expect(service).toBeTruthy();
    }
  ));
});

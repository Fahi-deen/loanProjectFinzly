package com.loan.service;

import java.util.List;

import com.loan.model.CustomerDetails;
import com.loan.model.PaymentSchedule;

public interface CustomerService {

	CustomerDetails createLoan(CustomerDetails userDetails);

	List<CustomerDetails> displayAllLoans();

	PaymentSchedule updatePaymentStatus(PaymentSchedule paidCustomer);

	List<PaymentSchedule> CurrentCustomerPaymentDetails(Long id);

}
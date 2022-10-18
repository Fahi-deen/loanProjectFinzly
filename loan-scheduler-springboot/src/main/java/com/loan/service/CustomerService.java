package com.loan.service;

import java.util.HashMap;
import java.util.List;

import com.loan.model.CustomerDetails;
import com.loan.model.PaymentSchedule;

public interface CustomerService {

	CustomerDetails createLoan(CustomerDetails userDetails);

	List<CustomerDetails> displayAllLoans();


	List<PaymentSchedule> CurrentCustomerPaymentDetails(Long id);

	HashMap<String, String> updatePaymentStatus(Long id);

}
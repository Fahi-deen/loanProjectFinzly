package com.loan.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loan.model.CustomerDetails;
import com.loan.model.PaymentSchedule;
import com.loan.service.CustomerService;

@RestController("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/createloan")
	public CustomerDetails createLoan(@Valid @RequestBody CustomerDetails customerDetails) {
	
		return customerService.createLoan(customerDetails);
	}

	@GetMapping("/allLoans")
	public List<CustomerDetails> displayAllLoans() {
		return customerService.displayAllLoans();
	}

	@GetMapping("/current/{id}")
	public List<PaymentSchedule> CurrentCustomerPaymentDetails(@PathVariable Long id) {
		
		return customerService.CurrentCustomerPaymentDetails(id);

	}

	@PutMapping("/updatePaymentStatus")
	public HashMap<String, String> updatePaymentStatus(@RequestBody PaymentSchedule payment) {
		customerService.updatePaymentStatus(payment);
		HashMap<String, String> resultMap = new HashMap<>();
		resultMap.put("Status", "Paid Successfully");
		return resultMap;
	}
}

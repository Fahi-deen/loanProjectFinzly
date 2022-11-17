package com.loan.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.enumeration.PaymentStatus;
import com.loan.enumeration.PaymentTerm;
import com.loan.exception.ResourceNotFoundException;
import com.loan.model.CustomerDetails;
import com.loan.model.PaymentSchedule;
import com.loan.repository.CustomerDetailsRepository;
import com.loan.repository.PaymentScheduleRepository;
import com.loan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	@Autowired
	private PaymentScheduleRepository paymentScheduleRepository;

	@Override
	public CustomerDetails createLoan(CustomerDetails userDetails) {
		CustomerDetails customer = new CustomerDetails();

		String customerName = userDetails.getCustomerName();
		
		Float customerLoanAmount = userDetails.getLoanAmount();
		LocalDate customerLoanStartDate = userDetails.getLoanStartDate();
		PaymentTerm paymentTerm = userDetails.getPaymentTerm();
		LocalDate customerMaturityDate=userDetails.getLoanStartDate().plusMonths(userDetails.getNoOfMonths());
		Integer customerPaymentFrequency = userDetails.getPaymentFrequency();
		Float customerInterestRate = userDetails.getInterestRate();
		
		customer.setMaturityDate(customerMaturityDate);
		customer.setCustomerName(customerName);
		customer.setLoanAmount(customerLoanAmount);
		customer.setLoanStartDate(customerLoanStartDate);
		customer.setNoOfMonths(userDetails.getNoOfMonths());
		customer.setPaymentTerm(paymentTerm);
		customer.setPaymentFrequency(customerPaymentFrequency);
		customer.setInterestRate(customerInterestRate);

		if (userDetails.getPaymentTerm().equals(PaymentTerm.INTEREST_ONLY)) {
			customer.setPaymentSchedule(interestOnlyPaymentCalculator(userDetails));
		} else {
			customer.setPaymentSchedule(evenPaymentFrequencyCalculator(userDetails));
		}
	

		return customerDetailsRepository.save(customer);

	}

	private List<PaymentSchedule> interestOnlyPaymentCalculator(CustomerDetails customer) {
		List<PaymentSchedule> paymentList = new ArrayList<>();
		float principalAmount = customer.getLoanAmount();
		int noOfMonths = customer.getNoOfMonths();
		int installment = (noOfMonths / customer.getPaymentFrequency());
		float rate = customer.getInterestRate();
		LocalDate paymentDate = null;
          
		PaymentStatus paymentStatus=PaymentStatus.PROJECTED;
		float interestRate=rate /100;
		float perMonthInterest = (principalAmount *interestRate)  ;
		float projectedInterest=perMonthInterest * customer.getPaymentFrequency();
		for (int i = 0; i < installment; i++) {
			if (i == 0) {
				paymentDate = customer.getLoanStartDate().plusMonths(customer.getPaymentFrequency());
			} else
				paymentDate = paymentDate.plusMonths(customer.getPaymentFrequency());
			if (i == installment - 1) {
				paymentList.add(new PaymentSchedule(paymentDate, principalAmount, projectedInterest, paymentStatus,
						(principalAmount + projectedInterest), customer.getCustomerName()));
			} else
				paymentList.add(new PaymentSchedule(paymentDate, 0f, projectedInterest, paymentStatus,
						projectedInterest, customer.getCustomerName()));
		}
		return paymentList;
	}

	private List<PaymentSchedule> evenPaymentFrequencyCalculator(CustomerDetails customer) {
		List<PaymentSchedule> paymentList = new ArrayList<>();
		int noOfMonths = customer.getNoOfMonths();
		float numberOfYears = noOfMonths / 12;
		int installment = (noOfMonths / customer.getPaymentFrequency());
		float principalAmount = customer.getLoanAmount();
		float defaultPaymentAmount = (principalAmount / installment);
		float paymentAmount;
		LocalDate evenLocalDate = customer.getLoanStartDate();
		float projectedInterest;
		PaymentStatus paymentStatus;
		LocalDate paymentDate = null;
		float rate = customer.getInterestRate();
		for (int i = 0; i < installment; i++) {
			if (i > 0)
				principalAmount = principalAmount - defaultPaymentAmount;
			projectedInterest = (principalAmount * numberOfYears * rate) / 100;
			paymentAmount = defaultPaymentAmount + projectedInterest;
			paymentStatus = PaymentStatus.PROJECTED;
			if (i == 0) {
				paymentDate = evenLocalDate.plusMonths(customer.getPaymentFrequency());
			} else
				paymentDate = paymentDate.plusMonths(customer.getPaymentFrequency());
			paymentList.add(new PaymentSchedule(paymentDate, principalAmount, projectedInterest, paymentStatus,
					paymentAmount, customer.getCustomerName()));
		}

		return paymentList;
	}

	@Override
	public List<CustomerDetails> displayAllLoans() {
		List<CustomerDetails> allLoansData = customerDetailsRepository.findAll();
		return allLoansData;
	}

	@Override
	public PaymentSchedule updatePaymentStatus(PaymentSchedule paidCustomer) {
		String errorMsg ="PaymentID "+ paidCustomer.getPaymentID()+" is not found";
		PaymentSchedule foundPayment = paymentScheduleRepository.findById(paidCustomer.getPaymentID())
				.orElseThrow(()->new ResourceNotFoundException(errorMsg));
		foundPayment.setPaymentStatus(PaymentStatus.PAID);
     
		return paymentScheduleRepository.save(foundPayment);

	}

	@Override
	public List<PaymentSchedule> CurrentCustomerPaymentDetails(Long id) {
		String errorMsg ="Customer with ID "+id+" is not found";
		CustomerDetails currentCustomerList = customerDetailsRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(errorMsg));
		return checkCurrentPaymentDay(currentCustomerList.getPaymentSchedule());
	}

	private List<PaymentSchedule> checkCurrentPaymentDay(List<PaymentSchedule> datas) {
		List<PaymentSchedule> currentPaymentList = datas;
		LocalDate currentDate = LocalDate.now();
		for (PaymentSchedule current : currentPaymentList) {

			if (currentDate.compareTo(current.getPaymentDate()) == 0
					&& current.getPaymentStatus().equals(PaymentStatus.PROJECTED)) {
				current.setPaymentStatus(PaymentStatus.AWAITINGPAYMENT);
			}

		}

		paymentScheduleRepository.saveAll(currentPaymentList);
		return currentPaymentList;
	}

}

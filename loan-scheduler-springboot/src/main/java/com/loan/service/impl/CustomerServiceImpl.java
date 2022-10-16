package com.loan.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.dto.CustomerPaymentDTO;
import com.loan.enumeration.PaymentStatus;
import com.loan.enumeration.PaymentTerm;
import com.loan.model.CustomerDetails;
import com.loan.model.PaymentSchedule;
import com.loan.repository.CustomerDetailsRepository;
import com.loan.repository.PaymentScheduleRepository;

@Service
public class CustomerServiceImpl {
	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	@Autowired
	private PaymentScheduleRepository paymentScheduleRepository;

	public CustomerDetails createLoan(CustomerPaymentDTO userDetails) {
		CustomerDetails customer = new CustomerDetails();

		String customerName = userDetails.getCustomerName().trim();
		Float customerLoanAmount = userDetails.getLoanAmount();
		LocalDate customerTradeDate = LocalDate.now();
		LocalDate customerLoanStartDate = LocalDate.now().plusDays(userDetails.getLoanStartDate());
		PaymentTerm paymentTerm=userDetails.getPaymentTerm();
		LocalDate customerMaturityDate = customerLoanStartDate.plusMonths(userDetails.getNoOfMonths());
		Integer customerPaymentFrequency = userDetails.getPaymentFrequency();
		Float customerInterestRate = userDetails.getInterestRate();

		customer.setCustomerName(customerName);
		customer.setLoanAmount(customerLoanAmount);
		customer.setTradeDate(customerTradeDate);
		customer.setLoanStartDate(customerLoanStartDate);
		customer.setMaturityDate(customerMaturityDate);
		customer.setPaymentTerm(paymentTerm);
		customer.setPaymentFrequency(customerPaymentFrequency);
		customer.setInterestRate(customerInterestRate);

		if (userDetails.getPaymentTerm().equals(PaymentTerm.INTEREST_ONLY)) {
			customer.setPaymentSchedule(interestOnlyPaymentCalculator(userDetails, customerLoanStartDate));
		} else {
			List<PaymentSchedule> paymentlistCalculated = evenPaymentFrequencyCalculator(userDetails,
					customerLoanStartDate);
			customer.setPaymentSchedule(paymentlistCalculated);
		}

		return customerDetailsRepository.save(customer);

	}

	private List<PaymentSchedule> interestOnlyPaymentCalculator(CustomerPaymentDTO customer, LocalDate loanStartDate) {
		List<PaymentSchedule> paymentList = new ArrayList<>();
		float principalAmount = customer.getLoanAmount();
		int installment = (customer.getNoOfMonths() / customer.getPaymentFrequency());
		float rate = customer.getInterestRate();
		LocalDate paymentDate = null;
		PaymentStatus paymentStatus;
		float projectedInterest = ((principalAmount* rate) / 100) / 12;
		for (int i = 0; i < installment; i++) {
			paymentStatus = PaymentStatus.PROJECTED;
			if (i == 0) {
				paymentDate = loanStartDate.plusMonths(customer.getPaymentFrequency());
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

	private List<PaymentSchedule> evenPaymentFrequencyCalculator(CustomerPaymentDTO customer, LocalDate loanStartDate) {
		List<PaymentSchedule> paymentList = new ArrayList<>();
		float numberOfYears = customer.getNoOfMonths() / 12;
		int installment = (customer.getNoOfMonths() / customer.getPaymentFrequency());
		float principalAmount = customer.getLoanAmount();
		float defaultPaymentAmount = (principalAmount / installment);
		float paymentAmount;
		LocalDate evenLocalDate = loanStartDate;
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

	public List<CustomerDetails> displayAllLoans() {
		List<CustomerDetails> allLoansData = customerDetailsRepository.findAll();
		return allLoansData;
	}

	public PaymentSchedule updatePaymentStatus(PaymentSchedule paidCustomer) {
		PaymentSchedule foundPayment = paymentScheduleRepository.findById(paidCustomer.getPaymentID()).get();
		
		foundPayment.setPaymentStatus(PaymentStatus.PAID);
		return paymentScheduleRepository.save(foundPayment);

	}

	public List<PaymentSchedule> CurrentCustomerPaymentDetails(Long id) {
		CustomerDetails currentCustomerList = customerDetailsRepository.findById(id).get();
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

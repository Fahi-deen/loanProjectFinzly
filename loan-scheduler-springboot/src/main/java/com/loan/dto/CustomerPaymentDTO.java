package com.loan.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.loan.enumeration.PaymentTerm;

import lombok.Data;

@Data
public class CustomerPaymentDTO {
	private String customerName;

	private Float loanAmount;
	private Integer loanStartDate;
	private Integer paymentFrequency;
	private Float interestRate;
	private Integer noOfMonths;
	@Enumerated(EnumType.STRING)
	private PaymentTerm paymentTerm;
}

package com.loan.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.loan.enumeration.PaymentStatus;

import lombok.Data;

@Entity
@Data

public class PaymentSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PaymentID;
	private LocalDate paymentDate;
	private Float principalAmount;
	private Float projectedInterest;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus = PaymentStatus.PROJECTED;
	private Float paymentAmount;

	private String customerName;

	public PaymentSchedule(LocalDate paymentDate, Float principalAmount, Float projectedInterest,
			PaymentStatus paymentStatus, Float paymentAmount, String customerName) {
		this.paymentDate = paymentDate;
		this.principalAmount = principalAmount;
		this.projectedInterest = projectedInterest;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
		this.customerName = customerName;
	}

	public PaymentSchedule() {

	}

}

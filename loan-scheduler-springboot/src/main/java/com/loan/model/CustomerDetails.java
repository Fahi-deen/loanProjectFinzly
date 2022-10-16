package com.loan.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.loan.enumeration.PaymentTerm;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "customer_details")
public class CustomerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerID;
	@NotEmpty
	private String customerName;
	@NotNull
	@Range(min = 1, message = "length must be above 0")
	private Float loanAmount;
	private LocalDate TradeDate;
	@NotNull
	private LocalDate loanStartDate;
	@NotNull
	private LocalDate maturityDate;
	@NotNull
	@Enumerated(EnumType.STRING)
	private PaymentTerm paymentTerm;
	@NotNull
	private Integer paymentFrequency;
	@NotNull
	private Float interestRate;
	@OneToMany(cascade = CascadeType.ALL)
	private List<PaymentSchedule> paymentSchedule;
}

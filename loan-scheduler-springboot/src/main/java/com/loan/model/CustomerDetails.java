package com.loan.model;

import java.time.LocalDate;
import java.util.Date;
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
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Pattern(regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,}$", message = "Invalid Customer Name.."
			+ " Enter only Letters and Spaces and minimum length should 4..." + "name must end with space")
	@NotEmpty
	private String customerName;
	@NotNull
	@Range(min = 1, message = "length must be above 0")
	private Float loanAmount;
	@CreationTimestamp
	private Date tradeDate;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate loanStartDate;
	@NotNull
	private LocalDate maturityDate;
	@Enumerated(EnumType.STRING)
	@NotNull
	private PaymentTerm paymentTerm;
	@NotNull
	private Integer paymentFrequency;
	@NotNull
	private Float interestRate;
	@OneToMany(cascade = CascadeType.ALL)
	private List<PaymentSchedule> paymentSchedule;
}

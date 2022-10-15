package com.loan.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	private Integer paymentFrequency;
	@NotNull
	private Float interestRate;

	@OneToMany(cascade = CascadeType.ALL)
	private List<PaymentSchedule> paymentSchedule;
}

package com.loan;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.loan.enumeration.PaymentTerm;
import com.loan.model.CustomerDetails;
import com.loan.repository.CustomerDetailsRepository;

@DataJpaTest
class LoanSchedulerFinzlyApplicationTests {

	@Autowired
	private CustomerDetailsRepository testcustomerDetailsRepository;
	
	
	public void checkcreateCustomer() {
		LocalDate loanStartDate = LocalDate.of(2022, Month.OCTOBER, 19);
		LocalDate maturityDate = LocalDate.of(2023, Month.OCTOBER, 19);
		CustomerDetails customerDetails=new CustomerDetails();
		customerDetails.setCustomerID(2L);
		customerDetails.setCustomerName("Hameed");
		customerDetails.setInterestRate(10f);
		customerDetails.setLoanAmount(24000.0f);
		customerDetails.setLoanStartDate(loanStartDate);
		customerDetails.setMaturityDate(maturityDate);
		customerDetails.setNoOfMonths(12);
		customerDetails.setPaymentFrequency(1);
		customerDetails.setPaymentTerm(PaymentTerm.EVEN_PRINCIPLE);
		customerDetails.setPaymentSchedule(null);
		testcustomerDetailsRepository.save(customerDetails);
		CustomerDetails expected=testcustomerDetailsRepository.findById(2L).get();
		assertThat(expected).isNotNull();
		
	}
	
	public void checkpaymentStatus() {
		
	}

}

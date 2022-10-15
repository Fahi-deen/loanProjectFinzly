package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.model.PaymentSchedule;
@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {
}

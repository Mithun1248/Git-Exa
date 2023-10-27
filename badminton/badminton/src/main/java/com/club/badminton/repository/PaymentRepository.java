package com.club.badminton.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.badminton.entity.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Integer>{

	public List<PaymentDetails> findByPhoneNumber(long id);
}

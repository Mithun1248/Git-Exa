package com.club.badminton.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PaymentDetails {

	@Id
	@GeneratedValue
	private int id;
	
	private long phoneNumber;
	
	private LocalDateTime dateOfPayment;
	
	private long ammountPaid;

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDateTime dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public long getAmmountPaid() {
		return ammountPaid;
	}

	public void setAmmountPaid(long ammountPaid) {
		this.ammountPaid = ammountPaid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

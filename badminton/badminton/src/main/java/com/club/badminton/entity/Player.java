package com.club.badminton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {

	@Id
	private long phoneNumber;
	
	private String name;
	
	private String password;
	
	private long accountBalance;

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

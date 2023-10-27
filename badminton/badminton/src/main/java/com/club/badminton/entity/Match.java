package com.club.badminton.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="match_details")
@Entity
public class Match {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int courtNo;
	
	@Enumerated(EnumType.STRING)
	private MatchStatus matchStatus;
	
	@Column(name="player_1_phone_no")
	private long player1PhoneNo;
	
	@Column(name="player_2_phone_no")
	private long player2PhoneNo;
	
	private LocalDateTime startDateTime;
	
	private LocalDateTime endDateTime;
	
	private long loserPhoneNumber;
	
	private long ammount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourtNo() {
		return courtNo;
	}

	public void setCourtNo(int courtNo) {
		this.courtNo = courtNo;
	}

	public MatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(MatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}

	public long getPlayer1PhoneNo() {
		return player1PhoneNo;
	}

	public void setPlayer1PhoneNo(long player1PhoneNo) {
		this.player1PhoneNo = player1PhoneNo;
	}

	public long getPlayer2PhoneNo() {
		return player2PhoneNo;
	}

	public void setPlayer2PhoneNo(long player2PhoneNo) {
		this.player2PhoneNo = player2PhoneNo;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public long getLoserPhoneNumber() {
		return loserPhoneNumber;
	}

	public void setLoserPhoneNumber(long loserPhoneNumber) {
		this.loserPhoneNumber = loserPhoneNumber;
	}

	public long getAmmount() {
		return ammount;
	}

	public void setAmmount(long ammount) {
		this.ammount = ammount;
	}
	
}

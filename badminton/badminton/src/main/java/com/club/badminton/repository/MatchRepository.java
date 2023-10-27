package com.club.badminton.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.club.badminton.entity.Match;
import com.club.badminton.entity.MatchStatus;

public interface MatchRepository extends JpaRepository<Match, Integer>{

	public List<Match> findByCourtNoAndEndDateTimeIsNull(int num);
	public List<Match> findByCourtNoAndMatchStatus(int num, MatchStatus status);
	public List<Match> findByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
	public List<Match> findByPlayer1PhoneNoAndStartDateTimeBetween(long id,LocalDateTime start, LocalDateTime end);
	public List<Match> findByPlayer2PhoneNoAndStartDateTimeBetween(long id,LocalDateTime start, LocalDateTime end);
	@Query("SELECT DISTINCT courtNo FROM Match WHERE endDateTime IS NULL")
	public List<Integer> findCourtNoWithOngoingMatches();
}

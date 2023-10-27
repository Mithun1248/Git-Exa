package com.club.badminton.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.club.badminton.entity.Match;
import com.club.badminton.entity.MatchStatus;
import com.club.badminton.entity.Player;
import com.club.badminton.repository.MatchRepository;
import com.club.badminton.repository.PlayerRepository;

@RestController
public class MatchController {

	@Autowired
	private MatchRepository match;
	
	@Autowired
	private PlayerRepository player;
	
	@PostMapping("/admin/match/start")
	public String startMatch(@RequestBody Match m) {
		if(player.findById(m.getPlayer1PhoneNo()).isPresent() && 
				player.findById(m.getPlayer2PhoneNo()).isPresent()) {
	   
			List<Integer> occupied = match.findCourtNoWithOngoingMatches();
			if(occupied.size()!=5) {
				for(Integer i=1;i<=5;i++){
					if(!occupied.contains(i)) {
						m.setStartDateTime(LocalDateTime.now());
						m.setCourtNo(i);
						m.setMatchStatus(MatchStatus.STARTED);
						match.save(m);
						return "Match Started!";
					}
				}
	    }
			else {
				return "There is no court available!";
			}
		}
		return "Player not present!";
	}
	
	@PutMapping("/admin/match/end{court}{phoneNo}")
	public String endMatch(@RequestParam int court,@RequestParam long phoneNo) {
		List<Match> matches = match.findByCourtNoAndEndDateTimeIsNull(court);
		if(matches.isEmpty())
			return "No matches!";
		Match m=matches.get(0);
		m.setEndDateTime(LocalDateTime.now());
		m.setLoserPhoneNumber(phoneNo);
		long min=ChronoUnit.MINUTES.between(m.getStartDateTime(), m.getEndDateTime());
		m.setAmmount(min*2);
		m.setMatchStatus(MatchStatus.ENDED);
		Optional<Player> p = player.findById(m.getLoserPhoneNumber());
		if(p.isPresent()) {			
			p.get().setAccountBalance(p.get().getAccountBalance()+(min*2));
			player.save(p.get());
			match.save(m);
			return "Match ended!";
		}
		return "Player not exists!";
	}
	
	@GetMapping("/admin/matchdetails/by{start}{end}{id}")
	public List<Match> getMatchDetails(@RequestParam LocalDateTime start,@RequestParam LocalDateTime end,@RequestParam long id){
		List<Match> l= new ArrayList<>();
		l.addAll(match.findByPlayer1PhoneNoAndStartDateTimeBetween(id,start, end));
		l.addAll(match.findByPlayer2PhoneNoAndStartDateTimeBetween(id,start, end));
		return l;
	}
	
	@GetMapping("/admin/matchdetails")
	public List<Match> getMatches(){
		return match.findAll();
	}
	
	@GetMapping("/admin/matchdetails/by{start}{end}")
	public List<Match> getMatchDetails(@RequestParam LocalDateTime start,@RequestParam LocalDateTime end){
		return match.findByStartDateTimeBetween(start, end);
	}
}

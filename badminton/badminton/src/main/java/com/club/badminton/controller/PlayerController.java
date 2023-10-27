package com.club.badminton.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.club.badminton.entity.Player;
import com.club.badminton.repository.PlayerRepository;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerRepository player;

	@GetMapping("/admin/details")
	public List<Player> getPlayers(){
		return player.findAll();
	}
	
	@PostMapping("/admin/createplayer")
	public String createPlayer(@RequestBody Player p) {
		if(player.findById(p.getPhoneNumber()).isPresent())
			return "Player exists!";
		player.save(p);
		return "Player created!";
	}
	
	@GetMapping("/admin/getplayer{id}")
	public Optional<Player> getPlayerById(@RequestParam long id){
		return player.findById(id);
	}
}

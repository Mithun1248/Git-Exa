package com.club.badminton.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.club.badminton.entity.PaymentDetails;
import com.club.badminton.entity.Player;
import com.club.badminton.repository.PaymentRepository;
import com.club.badminton.repository.PlayerRepository;

@RestController
public class PayController {
	
	@Autowired
	private PlayerRepository player;
	
	@Autowired
	private PaymentRepository pay;

	@GetMapping("/admin/paydetails")
	public List<PaymentDetails> getPaymentDetails(){
		return pay.findAll();
	}
	
	@GetMapping("/admin/paydetails{id}")
	public List<PaymentDetails> getdetails(@RequestParam long id){
		return pay.findByPhoneNumber(id);
	}
	
	@PostMapping("/admin/pay")
	public String createPayment(@RequestParam long id,@RequestParam long ammount) {
		Optional<Player> p=player.findById(id);
		if(p.isPresent()) {
			PaymentDetails p1 = new PaymentDetails();
			p.get().setAccountBalance(p.get().getAccountBalance()-ammount);
			p1.setAmmountPaid(ammount);
			p1.setDateOfPayment(LocalDateTime.now());
			p1.setPhoneNumber(id);
			pay.save(p1);
			return "Payment generated!";
		}
		return "Player not present!";
	}
}

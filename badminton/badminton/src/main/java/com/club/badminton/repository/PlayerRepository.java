package com.club.badminton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.club.badminton.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

}

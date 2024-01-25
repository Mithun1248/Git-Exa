package com.example.map.mapexa1.repo;

import com.example.map.mapexa1.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}

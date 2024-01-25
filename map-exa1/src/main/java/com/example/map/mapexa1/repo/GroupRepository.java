package com.example.map.mapexa1.repo;

import com.example.map.mapexa1.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<UserGroup,Integer> {

}

package com.example.map.mapexa1.repo;

import com.example.map.mapexa1.model.Student;
import com.example.map.mapexa1.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

}

package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(String course);
    List<Student>findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);
}

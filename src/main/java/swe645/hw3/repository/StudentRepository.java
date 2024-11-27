package swe645.hw3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import swe645.hw3.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
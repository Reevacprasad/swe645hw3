// Group: Alpha1
// File: StudentRepository.java
// Description: Repository interface for performing database operations on the Student entity.

package swe645.hw3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swe645.hw3.model.Student;

// Extends JpaRepository to provide CRUD operations for the Student entity
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}

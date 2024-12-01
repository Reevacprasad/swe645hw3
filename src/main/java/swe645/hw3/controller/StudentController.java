// Group: Alpha1
// File: StudentController.java
// Description: Handles HTTP requests for managing student data, including adding, updating, retrieving, and deleting records.

package swe645.hw3.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import swe645.hw3.model.Student;
import swe645.hw3.service.StudentService;

@RestController
@RequestMapping("/api/students") // Base URL for all student-related endpoints
public class StudentController {
    
    private StudentService studentService; 

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }
    
    // Create a new student
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
    }
    
    // Retrieve a list of all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    // Retrieve a student by ID
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId) {
        return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
    }
    
    // Update an existing student by ID
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
    }
    
    // Delete a student by ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id); 
        return new ResponseEntity<String>("Student deleted successfully.", HttpStatus.OK);
    }
    
    // Test endpoint for basic functionality
    @GetMapping("/hello")
    public String home() {
        return "Welcome to my application!";
    }
}

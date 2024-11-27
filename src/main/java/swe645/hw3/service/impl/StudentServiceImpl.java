package swe645.hw3.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import swe645.hw3.exception.ResourceNotFoundException;
import swe645.hw3.model.Student;
import swe645.hw3.repository.StudentRepository;
import swe645.hw3.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	public StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	@Override
	public Student getStudentById(long id) {
		Optional<Student> student = studentRepository.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		} else {
			throw new ResourceNotFoundException("Student", "Id", id);
		}
	}
	
	@Override
	public Student updateStudent(Student student, long id) {
		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setCity(student.getCity());
		existingStudent.setDateOfSurvey(student.getDateOfSurvey());
		existingStudent.setId(student.getId());
		existingStudent.setInterestSource(student.getInterestSource());
		existingStudent.setLikedMost(student.getLikedMost());
		existingStudent.setRecommendationLikelihood(student.getRecommendationLikelihood());
		existingStudent.setState(student.getState());
		existingStudent.setStreetAddress(student.getStreetAddress());
		existingStudent.setTelephoneNumber(student.getTelephoneNumber());
		existingStudent.setZip(student.getZip());

		studentRepository.save(existingStudent);
		return existingStudent;
	}
	
	@Override
	public void deleteStudent(long id) {
		studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
		studentRepository.deleteById(id);
	}
}
package com.springboot.student_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.student_management_system.dto.StudentDto;
import com.springboot.student_management_system.entity.Student;
import com.springboot.student_management_system.repository.StudentRepository;
import com.springboot.student_management_system.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private ModelMapper modelMapper;

	public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
		super();
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<StudentDto> findAllStudents() {
		List<Student> students = studentRepository.findAll();
		return students.stream().map((student) -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void createStudent(StudentDto studentDto) {
		Student student = modelMapper.map(studentDto, Student.class);
		studentRepository.save(student);
	}

	@Override
	public StudentDto getStudentById(long studentId) {
		Student student = studentRepository.findById(studentId).get();
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public void updateStudents(StudentDto studentDto) {
		studentRepository.save(modelMapper.map(studentDto, Student.class));
	}

	@Override
	public void deleteStudent(long studentId) {
		studentRepository.deleteById(studentId);
	}

}
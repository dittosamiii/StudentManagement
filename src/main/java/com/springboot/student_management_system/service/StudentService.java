package com.springboot.student_management_system.service;

import java.util.List;

import com.springboot.student_management_system.dto.StudentDto;

public interface StudentService {
	List<StudentDto> findAllStudents();

	void createStudent(StudentDto studentDto);

	StudentDto getStudentById(long studentId);

	void updateStudents(StudentDto studentDto);

	void deleteStudent(long studentId);
}

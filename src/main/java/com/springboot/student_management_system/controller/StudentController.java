package com.springboot.student_management_system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.student_management_system.dto.StudentDto;
import com.springboot.student_management_system.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public String findAllStudents(Model model) {
		List<StudentDto> students = studentService.findAllStudents();
		model.addAttribute("students", students);
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudent(Model model) {
		StudentDto studentDto = new StudentDto();
		model.addAttribute("student", studentDto);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("student", studentDto);
			return "create_student";
		}
		studentService.createStudent(studentDto);
		return "redirect:/students";
	}

	@GetMapping("/students/{studentId}/edit")
	public String editStudent(@PathVariable long studentId, Model model) {
		StudentDto student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "edit_student";
	}

	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable long studentId, @ModelAttribute("student") StudentDto studentDto,
			BindingResult result, Model model) {
		studentDto.setId(studentId);
		if (result.hasErrors()) {
			model.addAttribute("student", studentDto);
			return "edit_student";
		}
		studentService.updateStudents(studentDto);
		return "redirect:/students";
	}

	@GetMapping("/students/{studentId}/delete")
	public String deleteStudent(@PathVariable long studentId) {
		studentService.deleteStudent(studentId);
		return "redirect:/students";
	}

	@GetMapping("/students/{studentId}/view")
	public String viewStudent(@PathVariable long studentId, Model model) {
		StudentDto studentDto = studentService.getStudentById(studentId);
		model.addAttribute("student", studentDto);
		return "view_student";
	}
}

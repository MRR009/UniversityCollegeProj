package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findByStudentRollNo(String studentRollNo);
	
	public Student findByStudentName(String studentName);
	
	public Student findByStudentEmail(String studentName);
	
	public Student findByStudentUsername(String studentUsername);
	
	public String removeByStudentRollNo(String rollNo);
}
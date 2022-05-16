package com.stg.serviceinterfaces;

import java.util.List;

import com.stg.entity.Student;
import com.stg.exception.CustomExcepHandler;

public interface StudentService {
	
	public abstract Student createStudent(Student student)throws CustomExcepHandler;
	
	public abstract Student readyByStudentRollNo(String rollNo)throws CustomExcepHandler;
	
	public abstract List<Student> getAllStudents()throws CustomExcepHandler;
	
	public abstract Student updateStudent(Student student)throws CustomExcepHandler;
	
	public abstract Student updateStudentPassword(String rollNo , String password)throws CustomExcepHandler;

	public abstract String removeStudentByRollNo(String rollNo)throws CustomExcepHandler;
}

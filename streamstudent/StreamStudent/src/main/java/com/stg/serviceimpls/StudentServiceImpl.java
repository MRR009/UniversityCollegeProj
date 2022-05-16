package com.stg.serviceimpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stg.entity.Stream;
import com.stg.entity.Student;
import com.stg.exception.CustomExcepHandler;
import com.stg.repository.StreamRepository;
import com.stg.repository.StudentRepository;
import com.stg.serviceinterfaces.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StreamRepository streamRepository;

	/*---------------------------------------CREATE---------------------------------------------------- */

	@Override
	public Student createStudent(Student student) throws CustomExcepHandler {
		Stream stream = streamRepository.findByStreamCode(student.getStream().getStreamCode());
		if (stream != null) {
			int streamId = stream.getStreamId();
			student.getStream().setStreamId(streamId);
			return studentRepository.save(student);
		} else {
			throw new CustomExcepHandler("Please check. Either data already exists or wrong data entered.");
		}

	}

	/*---------------------------------------READ---------------------------------------------------- */

	@Override
	public Student readyByStudentRollNo(String rollNo) throws CustomExcepHandler {

		if (studentRepository.findByStudentRollNo(rollNo) != null) {
			return studentRepository.findByStudentRollNo(rollNo);
		} else {
			throw new CustomExcepHandler("Student With this roll number not found");
		}

	}

	@Override
	public List<Student> getAllStudents() throws CustomExcepHandler {
		if(studentRepository.findAll().size()>0) {
			return studentRepository.findAll();
		}else {
			throw new CustomExcepHandler("No Data Found");
		}
		
	}

	/*---------------------------------------UPDATE----------------------------------------------------*/

	@Override
	public Student updateStudent(Student student) throws CustomExcepHandler {
		Stream streamByCode = streamRepository.findByStreamCode(student.getStream().getStreamCode());
		Stream streamByName = streamRepository.findBystreamName(student.getStream().getStreamName());
		if (streamByCode != null) {
			int streamId = streamByCode.getStreamId();
			student.getStream().setStreamId(streamId);
			return studentRepository.save(student);
		} else if (streamByName != null) {
			int streamId = streamByName.getStreamId();
			student.getStream().setStreamId(streamId);
			return studentRepository.save(student);
		} else {
			throw new CustomExcepHandler("Couldn't update student data. Please give valid values");
		}

		// return tempStud;
	}

	@Override
	public Student updateStudentPassword(String rollNo, String password) throws CustomExcepHandler {

		Student student = studentRepository.findByStudentRollNo(rollNo);
		if (student != null) {
			student.setStudentPassword(password);
			return studentRepository.save(student);
		} else {
			throw new CustomExcepHandler("Student not found. Cannot update Data");
		}

	}

	/*---------------------------------------DELETE---------------------------------------------------- */
	@Transactional
	@Override
	public String removeStudentByRollNo(String rollNo) throws CustomExcepHandler {
		if (studentRepository.findByStudentRollNo(rollNo) != null) {
			String studName = studentRepository.findByStudentRollNo(rollNo).getStudentName();
			studentRepository.removeByStudentRollNo(rollNo);
			return "Student " + studName + "data with removed";
		} else {
			throw new CustomExcepHandler("Student not deleted, could not found student");
		}

	}

	/*---------------------------------------END---------------------------------------------------- */

}

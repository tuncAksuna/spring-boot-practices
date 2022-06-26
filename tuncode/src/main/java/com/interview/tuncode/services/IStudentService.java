package com.interview.tuncode.services;


import com.interview.tuncode.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentService {

    ResponseEntity<List<Student>> getStudents();

    Student createStudent(Student stu);

    ResponseEntity<Student> updateStudent(Long id, Student studentDetails);

    ResponseEntity<String> deleteStudent(Long id);

}

package com.interview.tuncode.services;


import com.interview.tuncode.configurations.customexception.response.AppResponse;
import com.interview.tuncode.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentService {

    AppResponse<List<Student>> getStudents();

    AppResponse<Student> createStudent(Student stu);

    AppResponse<Student> updateStudent(Long id, Student studentDetails);

    AppResponse<String> deleteStudent(Long id);


}

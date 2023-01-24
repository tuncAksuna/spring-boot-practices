package com.interview.tuncode.services.student;


import com.interview.tuncode.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getStudents();

    Student createStudent(Student stu);

    Student updateStudent(Long id, Student studentDetails);

    String deleteStudent(Long id);

    List<Student> getUpdatedStudents();

    Student getStudentById(Long id);
}

package com.interview.tuncode.services.student;


import com.interview.tuncode.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getStudents();

    void createStudent(Student stu);

    void updateStudent(Long id, Student studentDetails);

    Long deleteStudent(Long id);

    List<Student> getUpdatedStudents();

    Student getStudentById(Long id);

    long getStudentsCount();

    List<Student> getStudentsWithoutUsername();
}

package com.interview.tuncode.services.student;


import com.interview.tuncode.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IStudentService {

    Page<List<Student>> getStudents(int page, int size, String sortBy);
    
    void createStudent(Student stu);

    void updateStudent(Long id, Student studentDetails);

    Long deleteStudent(Long id);

    List<Student> getUpdatedStudents();

    Student getStudentById(Long id);

    long getStudentsCount();

    List<Student> getStudentsWithoutUsername();
}

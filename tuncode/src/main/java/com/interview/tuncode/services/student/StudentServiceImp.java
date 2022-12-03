package com.interview.tuncode.services.student;

import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class StudentServiceImp implements IStudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public AppResponse<List<Student>> getStudents() {
        log.info("All students were brought from the system");
        return new AppResponse<List<Student>>(studentRepository.findAll());
    }

    @Override
    public AppResponse<Student> createStudent(Student stu) {
        Optional<Student> studentOptional = studentRepository.findById(stu.getId());

        if (studentOptional.isPresent()) {
            log.info("[{}] [{}] already created", stu.getFirstName(), stu.getLastName());
            throw new SourceAlreadyExistsException("Student already exists in the system !" + stu.getId() + " " + stu.getFirstName() + " " + stu.getLastName());
        }

        Student newStudent = Student
                .builder()
                .firstName(stu.getFirstName())
                .lastName(stu.getLastName())
                .email(stu.getEmail())
                .createdTime(java.time.LocalTime.now().toString())
                .isUpdated(stu.isUpdated())
                .username(stu.getUsername())
                .secretText(stu.getSecretText())
                .studentRole(stu.getStudentRole())
                .build();


        log.info("[{}] [{}] has been created - at' [{}] ", stu.getFirstName(), stu.getLastName(), java.time.LocalTime.now().toString());

        return new AppResponse<Student>(studentRepository.save(newStudent));
    }

    @Override
    public AppResponse<Student> updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system ! " + "STUDENT DETAILS  " + " Id: " + studentDetails.getId() + " Firstname: " + studentDetails.getFirstName() + " E-Mail address: " + studentDetails.getEmail()));


        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setCreatedTime(java.time.LocalTime.now().toString());
        student.setUpdated(true);

        log.info("[{}] [{}] has been successfully Updated - at' [{}]  ", studentDetails.getFirstName(), studentDetails.getLastName(), student.getCreatedTime());

        return new AppResponse<Student>(studentRepository.save(student));
    }

    @Override
    public AppResponse deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system with ID: " + id));

        studentRepository.delete(student);

        log.info("[{}] [{}] has been successfully deleted from the system - at' [{}]", student.getFirstName(), student.getLastName(), student.getCreatedTime());

        return AppResponse.nullResponse();
    }
}

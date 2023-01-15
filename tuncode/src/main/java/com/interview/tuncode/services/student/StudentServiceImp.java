package com.interview.tuncode.services.student;

import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class StudentServiceImp implements IStudentService {

    private final StudentRepository studentRepository;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudents() {
        log.info("All students were brought from the system");
        return studentRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Student createStudent(Student stu) {
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
                .createdTime(new SimpleDateFormat(DATE_FORMAT).format(new Date()))
                .username(stu.getUsername())
                .secretText(stu.getSecretText())
                .studentRole(stu.getStudentRole())
                .build();


        log.info("[{}] [{}] has been created - at' [{}] ", stu.getFirstName(), stu.getLastName(), java.time.LocalTime.now().toString());

        return studentRepository.save(newStudent);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Student updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system ! " + " " + "id" + studentDetails.getId()));


        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setCreatedTime(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
        student.setUpdated(true);
        student.setSecretText(studentDetails.getSecretText());

        log.info("[{}] [{}] has been successfully Updated - at' [{}]  ", studentDetails.getFirstName(), studentDetails.getLastName(), new SimpleDateFormat(DATE_FORMAT).format(new Date().getTime()));

        return studentRepository.save(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system with ID: " + id));

        studentRepository.delete(student);

        log.info("[{}] [{}] has been successfully deleted from the system - at' [{}]", student.getFirstName(), student.getLastName(), student.getCreatedTime());

        return "Deleted student from the system";
    }

    @Override
    public List<Student> getUpdatedStudents() {
        return studentRepository.getUpdatedStudents();
    }
}

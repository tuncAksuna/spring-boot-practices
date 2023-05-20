package com.interview.tuncode.services.student;

import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.student.StudentRepository;
import com.interview.tuncode.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class StudentServiceImp implements IStudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudents() {
        log.info("All students brought from the system");
        return studentRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 3000)
    public Student createStudent(Student stu) {
//        Optional<Student> studentOptional = studentRepository.findById(stu.getId());

        Student student = studentRepository.findByUsername(stu.getUsername());

        if (student != null && student.getUsername().equalsIgnoreCase(stu.getUsername())) {
            log.warn("{} already created. Please try again with another username !", stu.getUsername());
            throw new SourceAlreadyExistsException(stu.getUsername() + " already created. Please try again with another username!");
        }

        Student newStudent = Student
                .builder()
                .firstName(stu.getFirstName())
                .lastName(stu.getLastName())
                .email(stu.getEmail())
                .createdTime(new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date()))
                .username(stu.getUsername())
                .secretText(stu.getSecretText())
                .studentRole(stu.getStudentRole())
                .build();


        log.info("{} {} has been created - at' {} ", stu.getFirstName(), stu.getLastName(), new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date().getTime()));

        return studentRepository.save(newStudent);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 3000)
    public Student updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system ! " + " " + "id " + studentDetails.getId()));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setCreatedTime(new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date()));
        student.setUpdated(true);
        student.setSecretText(studentDetails.getSecretText());

        log.info("{} {} has been successfully Updated - at' {}  ", studentDetails.getFirstName(), studentDetails.getLastName(), new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date().getTime()));

        return studentRepository.save(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 3000)
    public Long deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system with ID: " + id));

        studentRepository.delete(student);

        log.info("{} {} has been successfully deleted from the system !", student.getFirstName(), student.getLastName());

        return id;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getUpdatedStudents() {
        return studentRepository.getUpdatedStudents();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getStudentsCount() {
        return studentRepository.getStudentsCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudentsWithoutUsername() {
        return studentRepository.getStudentsWithoutUsername();
    }
}

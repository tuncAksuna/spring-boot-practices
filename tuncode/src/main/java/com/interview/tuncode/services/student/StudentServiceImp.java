package com.interview.tuncode.services.student;

import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Status;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.student.StudentRepository;
import com.interview.tuncode.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class StudentServiceImp implements IStudentService {

    private static final String ALREADY_EXISTS = " already exists. Please try again with another username";

    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 3000)
    public void createStudent(Student stu) {
        checkStudentAlready(stu);

        stu.setCreatedTime(new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date()));
        stu.setStatus(Status.getActiveObject());

        studentRepository.save(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 3000)
    public void updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.getStudentById(id);

        if (student.getId() == null) {
            throw new SourceNotFoundException("Student not found in the system ! " + studentDetails.getId());
        }

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
//        student.setAddress(studentDetails.getAddress());
        student.setUpdated(true);
        student.setUpdatedTime(new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date()));

        studentRepository.save(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 3000)
    public Long deleteStudent(Long id) {
        Student student = getStudentById(id);

        student.setStatus(Status.getLogicalDeletedObject());
        studentRepository.deleteStudent(id);

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

    private Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new SourceNotFoundException("Student not found in the system with ID: " + id));
    }

    private void checkStudentAlready(Student stu) {
        Student student = studentRepository.getStudentByUsername(stu.getUsername());

        if (student != null) {
            log.warn(student + ALREADY_EXISTS);
            throw new SourceAlreadyExistsException(stu.getUsername() + ALREADY_EXISTS);
        }
    }
}

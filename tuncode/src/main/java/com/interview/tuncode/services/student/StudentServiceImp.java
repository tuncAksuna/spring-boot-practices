package com.interview.tuncode.services.student;

import com.interview.tuncode.configurations.aspects.CalculatePerform;
import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Status;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class StudentServiceImp implements IStudentService {

    private static final String ALREADY_EXISTS = " already exists. Please try again with another username";
    private static final int TRANSACTION_TIMEOUT = 3000;

    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Cacheable(cacheNames = "allStudents")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @CalculatePerform
    public Page<List<Student>> getStudents(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return studentRepository.findAllPaginated(pageable);
    }

    @Override
    @CacheEvict(cacheNames = "allStudents", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, timeout = TRANSACTION_TIMEOUT)
    public void createStudent(Student stu) {
        checkStudentAlready(stu);

        stu.setStatus(Status.getActiveObject());

        studentRepository.save(stu);
    }

    @Override
    @CacheEvict(cacheNames = "allStudents", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, timeout = TRANSACTION_TIMEOUT)
    public void updateStudent(Long id, Student studentDetails) {

        Student student = studentRepository.getStudentById(id);

        if (student.getId() == null) {
            throw new SourceNotFoundException("Student not found in the system ! " + studentDetails.getId());
        }

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
//        student.setAddress(studentDetails.getAddress());
        student.setUpdated(true);

        studentRepository.save(student);
    }

    @Override
    @CacheEvict(cacheNames = "allStudents", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, timeout = TRANSACTION_TIMEOUT)
    public Long deleteStudent(Long id) {
        Student student = getStudentById(id);
        student.setStatus(Status.getLogicalDeletedObject());
        log.info("{} {} has been successfully deleted from the system !", student.getFirstName(), student.getLastName());

        return id;
    }

    @Override
    @Cacheable(cacheNames = "updateStudents", condition = "#result.size() > 50")
    @CalculatePerform
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getUpdatedStudents() {
        return studentRepository.getUpdatedStudents();
    }

    @Override
    @CalculatePerform
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    @CalculatePerform
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getStudentsCount() {
        return studentRepository.getStudentsCount();
    }

    @Override
    @CalculatePerform
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Student> getStudentsWithoutUsername() {
        return studentRepository.getStudentsWithoutUsername();
    }


    private void checkStudentAlready(Student stu) {
        Student student = studentRepository.getStudentByUsername(stu.getUsername());

        if (student != null) {
            log.warn(student + ALREADY_EXISTS);
            throw new SourceAlreadyExistsException(stu.getUsername() + ALREADY_EXISTS);
        }
    }
}

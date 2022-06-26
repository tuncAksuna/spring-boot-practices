package com.interview.tuncode;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.repository.StudentRepository;
import com.interview.tuncode.test.TestCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TuncodeApplicationTests implements TestCrud {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Override
    public void testCreate() {
        Student studentEntity = Student.builder()
                .firstName("Sylvie")
                .lastName("Oksana")
                .email("Oksana.Sylvie@gmail.com")
                .createdTime("")
                .isUpdated(false)
                .build();

        studentRepository.save(studentEntity);

        // If object is null send 'assertionError'
        assertNotNull(studentRepository.findById(1L).orElse(null));
    }

    @Test
    @Override
    public void testList() {
        List<Student> studentList = studentRepository.findAll();

        // If object is greater than zero, there is object
        assertThat(studentList).size().isGreaterThan(0);

    }

    @Test
    @Override
    public void testFindById() {
        Student studentEntity = studentRepository.findById(1L).get();
        assertEquals("Sylvie", studentEntity.getFirstName());
    }

    @Test
    @Override
    public void testUpdate() {
        Student studentEntity = studentRepository.findById(1L).orElse(null);

        assert studentEntity != null;
        studentEntity.setFirstName("Sylvie 06");
        studentEntity.setLastName("OKSANA");
        studentEntity.setUpdated(true);
        studentRepository.save(studentEntity);

        // If objects are equals, send exception
        assertNotEquals("Sylvie", Objects.requireNonNull(studentRepository.findById(1L).orElse(null)).getFirstName());
    }

    @Test
    @Override
    public void testDelete() {
        studentRepository.deleteById(1L);

        assertThat(studentRepository.existsById(1L)).isFalse();

    }
}

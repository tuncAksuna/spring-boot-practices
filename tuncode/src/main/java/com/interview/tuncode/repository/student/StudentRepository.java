package com.interview.tuncode.repository.student;

import com.interview.tuncode.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUsername(String username);

//    @Query("")
//    Long countStudentByUpdated(@Param("studentId") Long studentId);
}

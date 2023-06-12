package com.interview.tuncode.repository.student;

import com.interview.tuncode.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select std.id,std.username from Student std where std.username= :username")
    Student getStudentByUsername(@Param("username") String username);

    @Query("select std from Student std where std.isUpdated = true")
    List<Student> getUpdatedStudents();

    @Query("select std from Student std where std.id = :id")
    Student getStudentById(@Param("id") Long id);

    @Query("select count(std) from Student std")
    long getStudentsCount();

    @Query("select std from Student std where std.username is null")
    List<Student> getStudentsWithoutUsername();

}

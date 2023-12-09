package com.interview.tuncode.repository.student;

import com.interview.tuncode.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select std.id,std.username from Student std where std.username= :username and std.status.id = 1")
    Student getStudentByUsername(@Param("username") String username);

    @Query("select std from Student std where std.isUpdated = true and std.status.id = 1")
    List<Student> getUpdatedStudents();

    @Query("select std from Student std where std.id = :id and std.status.id = 1")
    Student getStudentById(@Param("id") Long id);

    @Query("select count(std) from Student std where std.status.id = 1")
    long getStudentsCount();

    @Query("select std from Student std where std.username is null and std.status.id = 1")
    List<Student> getStudentsWithoutUsername();

}

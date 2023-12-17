package com.interview.tuncode.repository;

import com.interview.tuncode.model.StudentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentCommentRepository extends JpaRepository<StudentComment, Long> {

    StudentComment getStudentCommentById(Long studentCommentId);

    @Query("select sc from StudentComment sc " +
            "where sc.student.id = :studentId")
    List<StudentComment> getStudentCommentByStudentId(@Param("studentId") Long studentId);
}

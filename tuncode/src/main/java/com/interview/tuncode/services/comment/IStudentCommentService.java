package com.interview.tuncode.services.comment;

import com.interview.tuncode.model.StudentComment;

import java.util.List;

public interface IStudentCommentService {

    Long deleteStudentComment(Long studentCommentId);

    String createComment(StudentComment comment, Long studentId);

    List<StudentComment> getAllStudentComment(Long studentId);
}

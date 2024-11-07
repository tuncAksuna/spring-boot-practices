package com.interview.tuncode.services.comment;

import com.interview.tuncode.configurations.aspects.CalculatePerform;
import com.interview.tuncode.exceptions.SourceAlreadyExistsException;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Status;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.StudentComment;
import com.interview.tuncode.repository.comment.StudentCommentRepository;
import com.interview.tuncode.services.student.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentCommentServiceImp implements IStudentCommentService {

    private static final int TRANSACTION_TIMEOUT = 3000;

    private final StudentCommentRepository studentCommentRepository;
    private final StudentServiceImp studentService;

    @Override
    @CalculatePerform
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, timeout = TRANSACTION_TIMEOUT)
    public List<StudentComment> getAllStudentComment(Long studentId) {
        if (studentId != null) {
            return studentCommentRepository.getStudentCommentByStudentId(studentId);
        }
        throw new SourceNotFoundException("Student not found !");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = SourceAlreadyExistsException.class)
    public String createComment(StudentComment comment, Long studentId) {
        if (studentId == null) {
            throw new SourceAlreadyExistsException("Student not found !");
        }
        Student student = studentService.getStudentById(studentId);

        comment.setStudent(student);
//        comment.setStatus(Status.getActiveObject());

        studentCommentRepository.save(comment);

        return "Comment has been successfully created !";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long deleteStudentComment(Long studentCommentId) {
        StudentComment studentComment = studentCommentRepository.getStudentCommentById(studentCommentId);

        if (studentComment != null) {
            studentComment.setStatus(Status.getLogicalDeletedObject());
            studentCommentRepository.save(studentComment);
            log.info("Deleted student comment with id : {} ", studentCommentId);
        }

        return studentCommentId;
    }
}

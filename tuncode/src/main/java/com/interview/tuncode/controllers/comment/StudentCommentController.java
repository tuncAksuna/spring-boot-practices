package com.interview.tuncode.controllers.comment;

import com.interview.tuncode.configurations.annotations.BusinessClass;
import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.StudentComment;
import com.interview.tuncode.services.comment.IStudentCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/student/comment")
@RequiredArgsConstructor
public class StudentCommentController {

    private final IStudentCommentService studentCommentService;

    @PostMapping("/create/{studentId}")
    public AppResponse<String> createStudentComment(@RequestBody @Valid StudentComment comment, @PathVariable Long studentId) {
        return new AppResponse<>(studentCommentService.createComment(comment, studentId));
    }

    @DeleteMapping("/delete/{id}")
    public AppResponse<Long> deleteStudentComment(@PathVariable
                                                  @BusinessClass(StudentComment.class) Long id) {

        return new AppResponse<>(studentCommentService.deleteStudentComment(id));
    }

    @GetMapping("/getStudentAllComment/{studentId}")
    public AppResponse<List<StudentComment>> getAllStudentComment(@PathVariable Long studentId) {
        return new AppResponse<>(studentCommentService.getAllStudentComment(studentId));
    }

}

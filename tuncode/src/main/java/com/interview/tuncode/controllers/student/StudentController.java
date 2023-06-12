package com.interview.tuncode.controllers.student;

import com.interview.tuncode.configurations.annotations.BusinessClass;
import com.interview.tuncode.configurations.mappers.IStudentMapper;
import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.requests.StudentCreateRequest;
import com.interview.tuncode.model.dtos.requests.StudentUpdateRequest;
import com.interview.tuncode.model.dtos.responses.StudentCreateResponse;
import com.interview.tuncode.model.dtos.responses.StudentGetResponse;
import com.interview.tuncode.model.dtos.responses.StudentUpdateResponse;
import com.interview.tuncode.services.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/student")
@CrossOrigin(value = "*")
public class StudentController {

    private final IStudentService iStudentService;

    @Autowired
    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    @GetMapping("/getAll")
    public AppResponse<List<StudentGetResponse>> getStudents() {
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentGetResponseList(iStudentService.getStudents()));
    }

    @PostMapping("/create")
    public AppResponse<StudentCreateResponse> createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        Student student = IStudentMapper.MAPPER.mapToStudentCreateEntity(studentCreateRequest);
        iStudentService.createStudent(student);
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentCreateResponse(student));
    }

    @PutMapping("/update/{id}")
    public AppResponse<StudentUpdateResponse> updateStudent(@PathVariable @BusinessClass(Student.class) Long id,
                                                            @RequestBody StudentUpdateRequest request) {
        Student student = IStudentMapper.MAPPER.mapToStudentUpdateEntity(request);
        student.setId(id);
        iStudentService.updateStudent(id, student);
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentUpdateResponse(student));
    }

    @DeleteMapping("/delete/{id}")
    public AppResponse<Long> deleteStudent(@PathVariable
                                           @BusinessClass(Student.class) Long id) {
        return new AppResponse<>(iStudentService.deleteStudent(id));
    }

    @GetMapping("/updatedStudents")
    public AppResponse<List<Student>> getUpdatedStudents() {
        return new AppResponse<>(iStudentService.getUpdatedStudents());
    }

    @GetMapping("/{id}")
    public AppResponse<Student> getStudentById(@PathVariable("id")
                                               @BusinessClass(Student.class) Long id) {
        return new AppResponse<>(iStudentService.getStudentById(id));
    }

    @GetMapping("/counts")
    public AppResponse<Long> getStudentsCount() {
        return new AppResponse<>(iStudentService.getStudentsCount());
    }

    @GetMapping("/studentsWithoutUsername")
    public AppResponse<List<Student>> getStudentsWithoutUsername() {
        return new AppResponse<>(iStudentService.getStudentsWithoutUsername());
    }

}

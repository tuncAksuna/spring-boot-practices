package com.interview.tuncode.controllers.student;

import com.interview.tuncode.configurations.annotations.BusinessClass;
import com.interview.tuncode.configurations.mappers.IStudentMapper;
import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.StudentDto;
import com.interview.tuncode.services.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin(value = "*")
public class StudentController {

    private final IStudentService iStudentService;

    @Autowired
    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    @GetMapping("/students")
    public AppResponse<List<Student>> getStudents() {
        return new AppResponse<>(iStudentService.getStudents());
    }

    @PostMapping("/create")
    public AppResponse<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        Student student = IStudentMapper.MAPPER.mapToStudent(studentDto);
        iStudentService.createStudent(student);
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentDto(student));
    }

    @PutMapping("/update/{id}")
    public AppResponse<StudentDto> updateStudent(@PathVariable @BusinessClass(Student.class) Long id,
                                                 @RequestBody StudentDto studentDto) {
        Student student = IStudentMapper.MAPPER.mapToStudent(studentDto);
        student.setId(id);
        iStudentService.updateStudent(id, student);
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentDto(student));
    }

    @DeleteMapping("/delete/{id}")
    public AppResponse<String> deleteStudent(@PathVariable @BusinessClass(Student.class) Long id) {
        return new AppResponse<>(iStudentService.deleteStudent(id));
    }

    @GetMapping("/updatedStudents")
    public AppResponse<List<Student>> getUpdatedStudents() {
        return new AppResponse<>(iStudentService.getUpdatedStudents());
    }

}

package com.interview.tuncode.controllers.student;

import com.interview.tuncode.configurations.mappers.IStudentMapper;
import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.StudentDto;
import com.interview.tuncode.services.student.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService iStudentService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public AppResponse<Page<List<Student>>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new AppResponse<>(iStudentService.getStudents(page, size, sortBy));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('client_admin')")
    public AppResponse<StudentDto> createStudent(@Valid @RequestBody StudentDto dto) {
        Student student = IStudentMapper.MAPPER.mapToStudent(dto);
        iStudentService.createStudent(student);
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentDto(student));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public AppResponse<StudentDto> updateStudent(@PathVariable Long id,
                                                 @RequestBody StudentDto dto) {
        Student student = IStudentMapper.MAPPER.mapToStudent(dto);
        student.setId(id);
        iStudentService.updateStudent(id, student);
        return new AppResponse<>(IStudentMapper.MAPPER.mapToStudentDto(student));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public AppResponse<Long> deleteStudent(@PathVariable Long id) {
        return new AppResponse<>(iStudentService.deleteStudent(id));
    }

    @GetMapping("/updatedStudents")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public AppResponse<List<Student>> getUpdatedStudents() {
        return new AppResponse<>(iStudentService.getUpdatedStudents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public AppResponse<Student> getStudentById(@PathVariable("id") Long id) {
        return new AppResponse<>(iStudentService.getStudentById(id));
    }

    @GetMapping("/counts")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public AppResponse<Long> getStudentsCount() {
        return new AppResponse<>(iStudentService.getStudentsCount());
    }

    @GetMapping("/studentsWithoutUsername")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public AppResponse<List<Student>> getStudentsWithoutUsername() {
        return new AppResponse<>(iStudentService.getStudentsWithoutUsername());
    }

}

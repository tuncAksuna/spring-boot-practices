package com.interview.tuncode.controllers;

import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Student;
import com.interview.tuncode.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/allStudents")
    @PreAuthorize("USER")
    public AppResponse<List<Student>> getStudents() {
        return iStudentService.getStudents();
    }

    @PostMapping("/create")
    @PreAuthorize("ADMIN")
    public AppResponse<Student> createStudent(@Valid @RequestBody Student student) {
        return iStudentService.createStudent(student);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("ADMIN")
    public AppResponse<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return iStudentService.updateStudent(id, student);
    }

    @PreAuthorize("SUPER_ADMIN")
    @DeleteMapping("/delete/{id}")
    public AppResponse<String> deleteStudent(@PathVariable Long id) {
        return iStudentService.deleteStudent(id);
    }

}

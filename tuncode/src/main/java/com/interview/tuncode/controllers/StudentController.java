package com.interview.tuncode.controllers;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.services.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> getStudents() {
        return iStudentService.getStudents();
    }

    @PostMapping("/create")
    public Student createStudent(@Valid @RequestBody Student student) {
        return iStudentService.createStudent(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return iStudentService.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return iStudentService.deleteStudent(id);
    }

}

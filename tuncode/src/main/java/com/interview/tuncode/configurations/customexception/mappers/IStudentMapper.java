package com.interview.tuncode.configurations.customexception.mappers;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IStudentMapper {

    IStudentMapper MAPPER = Mappers.getMapper(IStudentMapper.class);

    Student toStudent(StudentDto student);

    StudentDto toStudentDto(Student student);

    List<Student> toStudents(StudentDto student);

    List<StudentDto> toStudentsDto(Student student);
}

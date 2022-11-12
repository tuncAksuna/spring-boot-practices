package com.interview.tuncode.configurations.customexception.mappers;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

    IStudentMapper MAPPER = Mappers.getMapper(IStudentMapper.class);

    // dto to entity
    List<Student> toStudentsList(StudentDto student);

    Student toStudent(StudentDto student);

    // entity to dto
    List<StudentDto> toStudentsDtoList(Student student);

    StudentDto toStudentDto(Student student);
}

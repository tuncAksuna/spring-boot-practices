package com.interview.tuncode.configurations.mappers;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

    IStudentMapper MAPPER = Mappers.getMapper(IStudentMapper.class);

    List<Student> mapToStudentsList(List<StudentDto> dto);

    @Mappings({
            @Mapping(target = "email", source = "dto.emailAddress"),
    })
    Student mapToStudent(StudentDto dto);

    @Mappings({
            @Mapping(target = "emailAddress", source = "entity.email"),
    })
    StudentDto mapToStudentDto(Student entity);

}

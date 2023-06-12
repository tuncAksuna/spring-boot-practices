package com.interview.tuncode.configurations.mappers;

import com.interview.tuncode.model.Student;
import com.interview.tuncode.model.dtos.requests.StudentCreateRequest;
import com.interview.tuncode.model.dtos.requests.StudentUpdateRequest;
import com.interview.tuncode.model.dtos.responses.StudentCreateResponse;
import com.interview.tuncode.model.dtos.StudentDto;
import com.interview.tuncode.model.dtos.responses.StudentGetResponse;
import com.interview.tuncode.model.dtos.responses.StudentUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

    IStudentMapper MAPPER = Mappers.getMapper(IStudentMapper.class);

    List<Student> mapToStudentsList(List<StudentDto> dto);

    List<StudentDto> mapToStudentsDtoList(List<Student> entity);

    @Mappings({
            @Mapping(target = "email", source = "dto.emailAddress"),
            @Mapping(target = "secretText", source = "dto.token")
    })
    Student mapToStudent(StudentDto dto);

    @Mappings({
            @Mapping(target = "emailAddress", source = "entity.email"),
            @Mapping(target = "token", source = "entity.secretText")
    })
    StudentDto mapToStudentDto(Student entity);

    Student mapToStudentCreateEntity(StudentCreateRequest request);

    StudentCreateResponse mapToStudentCreateResponse(Student student);

    Student mapToStudentUpdateEntity(StudentUpdateRequest request);

    StudentUpdateResponse mapToStudentUpdateResponse(Student student);

    List<StudentGetResponse> mapToStudentGetResponseList(List<Student> entity);

}

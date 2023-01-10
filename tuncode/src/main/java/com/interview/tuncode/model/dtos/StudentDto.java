package com.interview.tuncode.model.dtos;

import lombok.Data;

@Data
public class StudentDto {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String createdTime;
    private boolean isUpdated;
    private Long token;
}

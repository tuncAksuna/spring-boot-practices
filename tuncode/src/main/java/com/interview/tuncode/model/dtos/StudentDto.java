package com.interview.tuncode.model.dtos;

import lombok.Data;

@Data
public class StudentDto {

    private String firstName;
    private String lastName;
    private String emailAddress;
    // "createdTime" temporarily given string.
    private String createdTime;
    private boolean isUpdated;
    private Long token;
    private String username;
}

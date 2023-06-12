package com.interview.tuncode.model.dtos.requests;

import lombok.Data;

@Data
public class StudentCreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String username;

}

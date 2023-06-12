package com.interview.tuncode.model.dtos.responses;

import com.interview.tuncode.model.Address;
import com.interview.tuncode.model.Status;
import lombok.Data;

@Data
public class StudentGetResponse {

    private String firstName;
    private String lastName;
    private String username;
    private Address address;
    private Status status;
    private String creationTime;
    private String updatedTime;
    private String studentRole;

}

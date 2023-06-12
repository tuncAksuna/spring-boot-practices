package com.interview.tuncode.model.dtos.requests;

import lombok.Data;

@Data
public class AddressCreateRequest {

    private String country;
    private String city;
    private String openAdress;
    private String shortDescription;
}

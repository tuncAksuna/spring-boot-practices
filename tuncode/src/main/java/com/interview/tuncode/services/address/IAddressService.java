package com.interview.tuncode.services.address;

import com.interview.tuncode.model.Address;

import java.util.List;


public interface IAddressService {

    Long getAddress(Long addressId);

    void createAddress(Address address);

    List<Address> getAllAdresses();

    Address getAddressByCountry(String country);
}

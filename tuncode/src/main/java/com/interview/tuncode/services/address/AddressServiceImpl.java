package com.interview.tuncode.services.address;

import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Address;
import com.interview.tuncode.model.Status;
import com.interview.tuncode.repository.address.AddressRepository;
import com.interview.tuncode.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    private static final String ADDRESS_ALREADY_EXISTS = " already created. Please try again with another 'short description' !";
    private static final String COUNTRY_NOT_FOUND = "Country not found. Please try again !";
    private static final int TRANSACTION_TIMEOUT = 3000;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, timeout = TRANSACTION_TIMEOUT)
    public Long getAddress(Long addressId) {
        if (addressId != null) {
            return addressRepository.getAddressById(addressId);
        }
        throw new SourceNotFoundException("Address not found !");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createAddress(Address adr) {
        Address address = addressRepository.findByShortDescription(adr.getShortDescription());
        if (address != null) {
            throw new SourceNotFoundException(adr.getCity() + ADDRESS_ALREADY_EXISTS);
        }
        adr.setStatus(Status.getActiveObject());
        adr.setCreatedTime(new SimpleDateFormat(DateUtils.ENUM.DATE_FORMAT.getValue()).format(new Date()));
        addressRepository.save(adr);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Address> getAllAdresses() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, timeout = TRANSACTION_TIMEOUT)
    public Address getAddressByCountry(String country) {
        if (country == null) {
            throw new SourceNotFoundException(COUNTRY_NOT_FOUND);
        }

        return addressRepository.getAddressByCountry(country.toUpperCase());
    }

}

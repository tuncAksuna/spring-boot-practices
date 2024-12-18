package com.interview.tuncode.services.address;

import com.interview.tuncode.configurations.aspects.CalculatePerform;
import com.interview.tuncode.exceptions.SourceNotFoundException;
import com.interview.tuncode.model.Address;
import com.interview.tuncode.model.Status;
import com.interview.tuncode.repository.address.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    private static final String ADDRESS_ALREADY_EXISTS = " already created. Please try again !";
    private static final String COUNTRY_NOT_FOUND = "Country not found. Please try again !";
    private static final int TRANSACTION_TIMEOUT = 3000;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @CalculatePerform
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
        addressRepository.save(adr);
    }

    @Override
    @CalculatePerform
    @Cacheable(cacheNames = "addresses")
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Address> getAllAdresses() {
        return addressRepository.findAll();
    }

    @Override
    @CalculatePerform
    @Transactional(propagation = Propagation.SUPPORTS, timeout = TRANSACTION_TIMEOUT)
    public Address getAddressByCountry(String country) {
        if (country == null) {
            throw new SourceNotFoundException(COUNTRY_NOT_FOUND);
        }

        return addressRepository.getAddressByCountry(country.toUpperCase());
    }

}

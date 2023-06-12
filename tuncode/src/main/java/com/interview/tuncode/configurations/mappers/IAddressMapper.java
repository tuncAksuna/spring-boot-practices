package com.interview.tuncode.configurations.mappers;

import com.interview.tuncode.model.Address;
import com.interview.tuncode.model.dtos.requests.AddressCreateRequest;
import com.interview.tuncode.model.dtos.responses.AddressCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IAddressMapper {

    IAddressMapper MAPPER = Mappers.getMapper(IAddressMapper.class);

    Address mapToAdressEntity(AddressCreateRequest request);

    AddressCreateResponse mapToAaddressCreateResponse(Address address);

}

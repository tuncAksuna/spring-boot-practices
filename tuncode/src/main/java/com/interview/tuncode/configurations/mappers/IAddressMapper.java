package com.interview.tuncode.configurations.mappers;

import com.interview.tuncode.model.Address;
import com.interview.tuncode.model.dtos.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IAddressMapper {

    IAddressMapper MAPPER = Mappers.getMapper(IAddressMapper.class);

    Address mapToAdressEntity(AddressDto dto);

    AddressDto mapToAddressDto(Address entity);
}

package com.interview.tuncode.controllers.address;

import com.interview.tuncode.configurations.annotations.BusinessClass;
import com.interview.tuncode.configurations.mappers.IAddressMapper;
import com.interview.tuncode.configurations.response.AppResponse;
import com.interview.tuncode.model.Address;
import com.interview.tuncode.model.dtos.AddressDto;
import com.interview.tuncode.services.address.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/address")
@RequiredArgsConstructor
public class AdressController {

    private final IAddressService iAddressService;

    @GetMapping("/getAddressById/{id}")
    public AppResponse<Long> getAddressById(@PathVariable("id") @BusinessClass(Address.class) Long id) {
        return new AppResponse<>(iAddressService.getAddress(id));
    }

    @GetMapping("/getAll")
    public AppResponse<List<Address>> getAllAdresses() {
        return new AppResponse<>(iAddressService.getAllAdresses());
    }

    @GetMapping("/getAddressByCountry/{country}")
    public AppResponse<Address> getAddressByCountry(@PathVariable("country") String country) {
        return new AppResponse<>(iAddressService.getAddressByCountry(country));
    }

    @PostMapping("/create")
    public AppResponse<AddressDto> createAddress(@Valid @RequestBody AddressDto dto) {
        Address address = IAddressMapper.MAPPER.mapToAdressEntity(dto);
        iAddressService.createAddress(address);
        return new AppResponse<>(IAddressMapper.MAPPER.mapToAddressDto(address));
    }
}

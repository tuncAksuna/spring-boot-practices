package com.interview.tuncode.repository.address;

import com.interview.tuncode.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select adrss from Address adrss where adrss.id = :addressId")
    Long getAddressById(@Param("addressId") Long addressId);

    @Query("select adrs from Address adrs where upper(adrs.country) like %:country%")
    Address getAddressByCountry(@Param("country") String country);

    Address findByShortDescription(String shortDescription);

}

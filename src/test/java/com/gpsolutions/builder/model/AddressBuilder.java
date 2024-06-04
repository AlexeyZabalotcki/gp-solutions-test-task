package com.gpsolutions.builder.model;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.model.Address;

public class AddressBuilder implements TestBuilder<Address> {

    private Long id = 1L;
    private Integer houseNumber = 9;
    private String street = "Pobediteley Avenue";
    private String city = "Minsk";
    private String county = "Belarus";
    private String postCode = "220004";

    @Override
    public Address build() {
        return Address.builder()
                .id(this.id)
                .houseNumber(this.houseNumber)
                .street(this.street)
                .city(this.city)
                .country(this.county)
                .postCode(this.postCode)
                .build();
    }
}

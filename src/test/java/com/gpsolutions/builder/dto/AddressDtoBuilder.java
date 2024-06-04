package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.AddressDto;

public class AddressDtoBuilder implements TestBuilder<AddressDto> {

    private Long id = 1L;
    private Integer houseNumber = 9;
    private String street = "Pobediteley Avenue";
    private String city = "Minsk";
    private String county = "Belarus";
    private String postCode = "220004";

    @Override
    public AddressDto build() {
        return AddressDto.builder()
                .houseNumber(this.houseNumber)
                .street(this.street)
                .city(this.city)
                .country(this.county)
                .postCode(this.postCode)
                .build();
    }
}

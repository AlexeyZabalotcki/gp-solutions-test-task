package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.AddressDto;
import com.gpsolutions.dto.ArrivalTimeDto;
import com.gpsolutions.dto.ContactsDto;
import com.gpsolutions.dto.CreateHotelDto;


public class CreateHotelDtoBuilder implements TestBuilder<CreateHotelDto> {

    private String name = "DoubleTree by Hilton Minsk";
    private String description = "Hotel Minsk";
    private String brand = "Hilton";

    private AddressDto address = new AddressDtoBuilder().build();

    private ContactsDto contacts = new ContactsDtoBuilder().build();
    private ArrivalTimeDto arrivalTime = new ArrivalTimeDtoBuilder().build();

    @Override
    public CreateHotelDto build() {
        return CreateHotelDto.builder()
                .name(this.name)
                .description(this.description)
                .brand(this.brand)
                .address(this.address)
                .contacts(this.contacts)
                .arrivalTime(this.arrivalTime)
                .build();
    }
}

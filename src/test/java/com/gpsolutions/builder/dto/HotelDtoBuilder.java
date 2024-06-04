package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.AddressDto;
import com.gpsolutions.dto.ArrivalTimeDto;
import com.gpsolutions.dto.ContactsDto;
import com.gpsolutions.dto.HotelDto;

import java.util.ArrayList;
import java.util.List;

public class HotelDtoBuilder implements TestBuilder<HotelDto> {

    private Long id = 1L;

    private String name = "DoubleTree by Hilton Minsk";
    private String description = "Hotel Minsk";
    private String brand = "Hilton";
    private AddressDto address = new AddressDtoBuilder().build();
    private ContactsDto contacts = new ContactsDtoBuilder().build();
    private ArrivalTimeDto arrivalTime = new ArrivalTimeDtoBuilder().build();
    private List<String> amenities = addToList();

    @Override
    public HotelDto build() {
        return HotelDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .brand(this.brand)
                .address(this.address)
                .contacts(this.contacts)
                .arrivalTime(this.arrivalTime)
                .amenities(this.amenities)
                .build();
    }

    private List<String> addToList() {
        List<String> amenities = new ArrayList<>();
        amenities.add("Free parking");
        return amenities;
    }
}

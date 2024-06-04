package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.HotelResponseDto;

public class HotelResponseDtoBuilder implements TestBuilder<HotelResponseDto> {

    private Long id = 1L;
    private String name = "DoubleTree by Hilton Minsk";
    private String description = "Hotel Minsk";

    private String address = "Minsk";
    private String phone = "+375291234567";

    @Override
    public HotelResponseDto build() {
        return HotelResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .address(this.address)
                .phone(this.phone)
                .build();
    }
}

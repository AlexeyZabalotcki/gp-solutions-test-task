package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.AmenityDto;

public class AmenityDtoBuilder implements TestBuilder<AmenityDto> {

    private Long id = 1L;

    private String name = "Free parking";

    @Override
    public AmenityDto build() {
        return AmenityDto.builder()
                .name(this.name)
                .build();
    }
}

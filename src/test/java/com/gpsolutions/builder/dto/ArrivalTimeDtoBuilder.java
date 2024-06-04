package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.ArrivalTimeDto;

import java.time.LocalTime;

public class ArrivalTimeDtoBuilder implements TestBuilder<ArrivalTimeDto> {

    private Long id = 1L;
    private LocalTime checkIn = LocalTime.of(14, 00);
    private LocalTime checkOut = LocalTime.of(12, 00);
    @Override
    public ArrivalTimeDto build() {
        return
                ArrivalTimeDto.builder()
                        .checkIn(this.checkIn)
                        .checkOut(this.checkOut)
                        .build();
    }
}

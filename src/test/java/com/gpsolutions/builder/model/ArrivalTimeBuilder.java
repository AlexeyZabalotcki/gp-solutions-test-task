package com.gpsolutions.builder.model;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.model.ArrivalTime;

import java.time.LocalTime;

public class ArrivalTimeBuilder implements TestBuilder<ArrivalTime> {

    private Long id = 1L;
    private LocalTime checkIn = LocalTime.of(14, 00);
    private LocalTime checkOut = LocalTime.of(12, 00);

    @Override
    public ArrivalTime build() {
        return
                ArrivalTime.builder()
                        .id(this.id)
                        .checkIn(this.checkIn)
                        .checkOut(this.checkOut)
                        .build();
    }
}

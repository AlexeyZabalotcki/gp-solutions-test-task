package com.gpsolutions.builder.model;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.model.Amenity;

public class AmenityBuilder implements TestBuilder<Amenity> {

    private Long id = 1L;
    private String name = "Free parking";

    @Override
    public Amenity build() {
        return Amenity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}

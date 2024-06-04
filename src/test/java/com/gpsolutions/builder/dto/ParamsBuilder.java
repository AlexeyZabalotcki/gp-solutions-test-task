package com.gpsolutions.builder.dto;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.dto.Params;

public class ParamsBuilder implements TestBuilder<Params> {

    private String name = "DoubleTree by Hilton Minsk";
    private String brand = "Hilton";
    private String city = "Minsk";
    private String county = "Belarus";
    private String amenity = "Free parking";

    @Override
    public Params build() {
        return Params.builder()
                .name(this.name)
                .brand(this.brand)
                .city(this.city)
                .country(this.county)
                .amenity(this.amenity)
                .build();
    }
}

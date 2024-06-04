package com.gpsolutions.builder.model;

import com.gpsolutions.builder.TestBuilder;
import com.gpsolutions.model.Address;
import com.gpsolutions.model.Amenity;
import com.gpsolutions.model.ArrivalTime;
import com.gpsolutions.model.Contacts;
import com.gpsolutions.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelBuilder implements TestBuilder<Hotel> {

    private Long id = 1L;
    private String name = "DoubleTree by Hilton Minsk";
    private String description = "Hotel Minsk";
    private String brand = "Hilton";
    private Address address = new AddressBuilder().build();
    private Contacts contacts = new ContactsBuilder().build();
    private ArrivalTime arrivalTime = new ArrivalTimeBuilder().build();
    private List<Amenity> amenities = addToList();

    @Override
    public Hotel build() {
        return Hotel.builder()
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

    private List<Amenity> addToList() {
        ArrayList<Amenity> amenities = new ArrayList<>();
        TestBuilder<Amenity> amenityTestBuilder = new AmenityBuilder();
        Amenity amenity = amenityTestBuilder.build();
        amenities.add(amenity);
        return amenities;
    }
}

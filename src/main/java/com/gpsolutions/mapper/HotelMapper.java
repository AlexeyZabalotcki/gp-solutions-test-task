package com.gpsolutions.mapper;

import com.gpsolutions.dto.CreateHotelDto;
import com.gpsolutions.dto.HotelDto;
import com.gpsolutions.dto.HotelResponseDto;
import com.gpsolutions.model.Amenity;
import com.gpsolutions.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "amenities", source = "hotel.amenities", qualifiedByName = "mapAmenities")
    HotelDto hotelToHotelDto(Hotel hotel);

    @Named("mapAmenities")
    default List<String> mapAmenities(List<Amenity> amenities) {
        if (amenities == null) {
            return null;
        }
        return amenities.stream()
                .map(Amenity::getName)
                .collect(Collectors.toList());
    }

    List<Amenity> map(List<String> value);

    Amenity map(String value);

    Hotel createHotelToHotel(CreateHotelDto createHotelDto);

    @Mappings({
            @Mapping(target = "address", source = "hotel", qualifiedByName = "formatAddress"),
            @Mapping(target = "phone", source = "hotel.contacts.phone")
    })
    HotelResponseDto hotelToHotelResponseDto(Hotel hotel);

    @Named("formatAddress")
    default String formatAddress(Hotel hotel) {
        if (hotel.getAddress() == null) {
            return null;
        }
        return String.format("%d %s, %s, %s, %s",
                hotel.getAddress().getHouseNumber(),
                hotel.getAddress().getStreet(),
                hotel.getAddress().getCity(),
                hotel.getAddress().getCountry(),
                hotel.getAddress().getPostCode());
    }

    Hotel hotelDtoToHotel(HotelDto hotelDto);
}

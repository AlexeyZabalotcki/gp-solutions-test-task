package com.gpsolutions.service;

import com.gpsolutions.dto.CreateHotelDto;
import com.gpsolutions.dto.HotelDto;
import com.gpsolutions.dto.HotelResponseDto;
import com.gpsolutions.dto.Params;

import java.util.List;
import java.util.Map;

public interface HotelService {

    List<HotelResponseDto> findAllHotels();
    HotelDto findHotelById(Long id);
    List<HotelResponseDto> searchHotels(Params params);

    HotelResponseDto create(CreateHotelDto createHotelDto);

    void  addAmenities(Long hotelId, List<String> amenities);

    Map<String, Long> getHotelsHistogram(String param);
}

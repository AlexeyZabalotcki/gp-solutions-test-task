package com.gpsolutions.service.impl;

import com.gpsolutions.dao.HotelRepository;
import com.gpsolutions.dto.CreateHotelDto;
import com.gpsolutions.dto.HotelDto;
import com.gpsolutions.dto.HotelResponseDto;
import com.gpsolutions.dto.Params;
import com.gpsolutions.mapper.HotelMapper;
import com.gpsolutions.model.Amenity;
import com.gpsolutions.model.Hotel;
import com.gpsolutions.service.HotelService;
import com.gpsolutions.util.CustomSpecification;
import com.gpsolutions.util.strategy.HistogramStrategy;
import com.gpsolutions.util.strategy.factory.StrategyFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    @Override
    public List<HotelResponseDto> findAllHotels() {
        return repository.findAll()
                .stream()
                .map(mapper::hotelToHotelResponseDto)
                .toList();
    }

    @Override
    public HotelDto findHotelById(Long id) {
        return repository.findById(id)
                .map(mapper::hotelToHotelDto)
                .orElseThrow(() -> new EntityNotFoundException("Check hotel's id"));
    }

    @Override
    public List<HotelResponseDto> searchHotels(Params params) {
        return repository.findAll(CustomSpecification.searchWithParams(params))
                .stream()
                .map(mapper::hotelToHotelResponseDto)
                .toList();
    }

    @Override
    public HotelResponseDto create(CreateHotelDto createHotelDto) {
        Hotel toSave = mapper.createHotelToHotel(createHotelDto);
        Hotel saved = repository.save(toSave);
        return mapper.hotelToHotelResponseDto(saved);
    }

    @Override
    public void addAmenities(Long hotelId, List<String> amenities) {
        Hotel hotel = repository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id: " + hotelId));
        List<Amenity> amenityList = amenities.stream()
                .map(name -> Amenity.builder().name(name).hotel(hotel).build())
                .toList();

        hotel.getAmenities().addAll(amenityList);
        repository.save(hotel);
    }

    @Override
    public Map<String, Long> getHotelsHistogram(String param) {
        StrategyFactory factory = new StrategyFactory(repository);
        HistogramStrategy strategy = factory.getStrategy(param);
        return strategy.getHistogram();
    }
}

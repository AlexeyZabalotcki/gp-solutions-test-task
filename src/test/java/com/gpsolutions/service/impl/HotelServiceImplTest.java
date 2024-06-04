package com.gpsolutions.service.impl;

import com.gpsolutions.builder.dto.CreateHotelDtoBuilder;
import com.gpsolutions.builder.dto.HotelDtoBuilder;
import com.gpsolutions.builder.dto.HotelResponseDtoBuilder;
import com.gpsolutions.builder.dto.ParamsBuilder;
import com.gpsolutions.builder.model.AmenityBuilder;
import com.gpsolutions.builder.model.HotelBuilder;
import com.gpsolutions.dao.HotelRepository;
import com.gpsolutions.dto.CreateHotelDto;
import com.gpsolutions.dto.HotelDto;
import com.gpsolutions.dto.HotelResponseDto;
import com.gpsolutions.dto.Params;
import com.gpsolutions.mapper.HotelMapper;
import com.gpsolutions.model.Amenity;
import com.gpsolutions.model.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @InjectMocks
    private HotelServiceImpl service;

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelMapper mapper;

    private static Hotel hotel;
    private static HotelDto hotelDto;
    private static CreateHotelDto createHotelDto;
    private static HotelResponseDto responseDto;
    private static Params params;
    private static Amenity amenity;
    private static List<HotelDto> dtoListHotels;
    private static List<HotelResponseDto> responseDtoList;
    private static List<Hotel> listHotels;


    @BeforeEach
    void setUp() {
        hotel = new HotelBuilder().build();
        hotelDto = new HotelDtoBuilder().build();
        createHotelDto = new CreateHotelDtoBuilder().build();
        responseDto = new HotelResponseDtoBuilder().build();
        amenity = new AmenityBuilder().build();
        params = new ParamsBuilder().build();

        dtoListHotels = new ArrayList<>();
        listHotels = new ArrayList<>();
        responseDtoList = new ArrayList<>();
        responseDtoList.add(responseDto);
        dtoListHotels.add(hotelDto);
        listHotels.add(hotel);
    }

    @Test
    void checkFindAllHotelShouldReturnAllHotels() {
        when(repository.findAll()).thenReturn(listHotels);
        when(mapper.hotelToHotelResponseDto(hotel)).thenReturn(responseDto);

        List<HotelResponseDto> actual = service.findAllHotels();

        assertEquals(responseDtoList, actual);

    }

    @Test
    void checkFindHotelByIdShouldReturnExpectedHotel() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(hotel));
        when(mapper.hotelToHotelDto(hotel)).thenReturn(hotelDto);

        HotelDto actual = service.findHotelById(1L);

        assertEquals(hotelDto, actual);

        verify(repository, times(1)).findById(anyLong());
        verify(mapper, times(1)).hotelToHotelDto(hotel);
    }

    @Test
    void checkSearchHotelsShouldReturnHotelsAccordingGivenParams() {
        when(repository.findAll(any(Specification.class))).thenReturn(listHotels);
        when(mapper.hotelToHotelResponseDto(hotel)).thenReturn(responseDto);

        List<HotelResponseDto> actual = service.searchHotels(params);

        assertEquals(responseDtoList, actual);

    }

    @Test
    void checkCreateShouldCreateNewHotel() {
        when(repository.save(mapper.createHotelToHotel(createHotelDto))).thenReturn(hotel);
        when(mapper.hotelToHotelResponseDto(hotel)).thenReturn(responseDto);

        HotelResponseDto actual = service.create(createHotelDto);

        assertEquals(responseDto, actual);

        verify(repository, times(1)).save(mapper.createHotelToHotel(createHotelDto));
        verify(mapper, times(1)).hotelToHotelResponseDto(hotel);
    }

    @Test
    void checkAddAmenitiesShouldAddNewAmenities() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(hotel));

        service.addAmenities(1L, List.of(amenity.getName()));

        ArgumentCaptor<Hotel> hotelArgumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(repository).save(hotelArgumentCaptor.capture());
        Hotel actual = hotelArgumentCaptor.getValue();

        assertNotNull(actual.getAmenities());
        assertEquals(2, actual.getAmenities().size());
    }

    @Test
    public void CheckGetHotelsHistogramByCityShouldReturnExpectedHotels() {
        List<Object[]> queryResult = Arrays.asList(
                new Object[]{"Minsk", 1L},
                new Object[]{"Moskow", 2L},
                new Object[]{"Mogilev", 0L});

        Map<String, Long> expected = queryResult.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1]
                ));

        when(repository.countHotelsGroupedByCity()).thenReturn(queryResult);

        String param = "city";

        Map<String, Long> actual = service.getHotelsHistogram(param);

        assertEquals(expected, actual);
        verify(repository).countHotelsGroupedByCity();
    }
}
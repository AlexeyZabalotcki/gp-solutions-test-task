package com.gpsolutions.controller;

import com.gpsolutions.dto.CreateHotelDto;
import com.gpsolutions.dto.HotelDto;
import com.gpsolutions.dto.HotelResponseDto;
import com.gpsolutions.dto.Params;
import com.gpsolutions.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/hotels")
public class HotelController {

    private final HotelService service;

    @Operation(summary = "Get all hotels", description = "Retrieve a list of all hotels with their details")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelResponseDto.class),
                    examples = @ExampleObject(
                            name = "HotelResponseDto Example",
                            value = "{\n" +
                                    "  \"id\": 1,\n" +
                                    "  \"name\": \"DoubleTree by Hilton Minsk\",\n" +
                                    "  \"description\": \"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...\",\n" +
                                    "  \"address\": \"9 Pobediteley Avenue, Minsk, Belarus, 220004\",\n" +
                                    "  \"phone\": \"+375 17 309-80-00\"\n" +
                                    "}"
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        List<HotelResponseDto> hotels = service.findAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @Operation(summary = "Get hotel by ID", description = "Retrieve a hotel by its ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelDto.class),
                    examples = @ExampleObject(
                            name = "HotelDtoExample",
                            value = "{\n" +
                                    "  \"id\": 1,\n" +
                                    "  \"name\": \"DoubleTree by Hilton Minsk\",\n" +
                                    "  \"description\": \"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...\",\n" +
                                    "  \"brand\": \"Hilton\",\n" +
                                    "  \"address\": {\n" +
                                    "    \"houseNumber\": 9,\n" +
                                    "    \"street\": \"Pobediteley Avenue\",\n" +
                                    "    \"city\": \"Minsk\",\n" +
                                    "    \"county\": \"Belarus\",\n" +
                                    "    \"postCode\": \"220004\"\n" +
                                    "  },\n" +
                                    "  \"contacts\": {\n" +
                                    "    \"phone\": \"+375 17 309-80-00\",\n" +
                                    "    \"email\": \"doubletreeminsk.info@hilton.com\"\n" +
                                    "  },\n" +
                                    "  \"arrivalTime\": {\n" +
                                    "    \"checkIn\": \"14:00:00\",\n" +
                                    "    \"checkOut\": \"12:00:00\"\n" +
                                    "  },\n" +
                                    "  \"amenities\": [\n" +
                                    "    \"Free parking\",\n" +
                                    "    \"Free WiFi\",\n" +
                                    "    \"Non-smoking rooms\",\n" +
                                    "    \"Concierge\",\n" +
                                    "    \"On-site restaurant\",\n" +
                                    "    \"Fitness center\",\n" +
                                    "    \"Pet-friendly rooms\",\n" +
                                    "    \"Room service\",\n" +
                                    "    \"Business center\",\n" +
                                    "    \"Meeting rooms\"\n" +
                                    "  ]\n" +
                                    "}"
                    )
            )
    )
    @ApiResponse(responseCode = "404", description = "Hotel not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelDto.class),
                    examples = @ExampleObject(
                            name = "Error: response status is 500 Example",
                            value = "{\n" +
                                    "  \"timestamp\": \"2024-06-04T15:23:13.688+00:00\",\n" +
                                    "  \"status\": 500,\n" +
                                    "  \"error\": \"Internal Server Error\",\n" +
                                    "  \"path\": \"/property-view/hotels/2\"\n" +
                                    "}"
                    )
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@Parameter(description = "ID of the hotel",
            example = "1")
                                                 @PathVariable Long id) {
        HotelDto hotel = service.findHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @Operation(summary = "Search hotels", description = "Search for hotels based on various parameters")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelResponseDto.class),
                    examples = @ExampleObject(
                            name = "HotelDtoExample",
                            value = "{\n" +
                                    "  \"id\": 1,\n" +
                                    "  \"name\": \"DoubleTree by Hilton Minsk\",\n" +
                                    "  \"description\": \"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...\",\n" +
                                    "  \"address\": \"9 Pobediteley Avenue, Minsk, Belarus, 220004\",\n" +
                                    "  \"phone\": \"+375 17 309-80-00\"\n" +
                                    "}"
                    )
            )
    )
    @GetMapping("/search")
    public ResponseEntity<List<HotelResponseDto>> searchHotels(@ParameterObject
                                                               @Parameter(description = "Search parameters", example = "{\"name\":\"Hilton\",\"city\":\"Minsk\"}")
                                                               Params params) {
        List<HotelResponseDto> hotels = service.searchHotels(params);
        return ResponseEntity.ok(hotels);
    }

    @Operation(summary = "Create a new hotel",
            description = "Create a new hotel with the provided details",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "CreateHotelDto Example",
                                    value = "{\n" +
                                            "\t\"name\": \"DoubleTree by Hilton Minsk\",\n" +
                                            "\t\"description\": \"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...\",\n" +
                                            "\t\"brand\": \"Hilton\",\n" +
                                            "\t\"address\": \n" +
                                            "\t\t{\n" +
                                            "\t\t    \"houseNumber\": 9,\n" +
                                            "\t\t\t\"street\": \"Pobediteley Avenue\",\n" +
                                            "\t\t\t\"city\": \"Minsk\",\n" +
                                            "\t\t\t\"country\": \"Belarus\",\n" +
                                            "\t\t\t\"postCode\": \"220004\"\n" +
                                            "\t\t},\n" +
                                            "\t\"contacts\": \n" +
                                            "\t\t{\n" +
                                            "\t\t\t\"phone\": \"+375 17 309-80-00\",\n" +
                                            "\t\t\t\"email\": \"doubletreeminsk.info@hilton.com\"\n" +
                                            "\t\t},\n" +
                                            "\t\"arrivalTime\":\n" +
                                            "\t\t{\n" +
                                            "\t\t\t\"checkIn\": \"14:00\",\n" +
                                            "\t\t\t\"checkOut\": \"12:00\"\n" +
                                            "\t\t}\n" +
                                            "\t}"
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelResponseDto.class),
                    examples = @ExampleObject(
                            name = "HotelResponseDto Example",
                            value = "{\n" +
                                    "  \"id\": 1,\n" +
                                    "  \"name\": \"DoubleTree by Hilton Minsk\",\n" +
                                    "  \"description\": \"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...\",\n" +
                                    "  \"address\": \"9 Pobediteley Avenue, Minsk, Belarus, 220004\",\n" +
                                    "  \"phone\": \"+375 17 309-80-00\"\n" +
                                    "}"
                    )
            )
    )
    @PostMapping
    public ResponseEntity<HotelResponseDto> createHotel(@RequestBody CreateHotelDto createHotelDto) {
        HotelResponseDto newHotel = service.create(createHotelDto);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
    }

    @Operation(summary = "Add amenities to a hotel",
            description = "Add a list of amenities to an existing hotel",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Add amenities example",
                                    value = "[\n" +
                                            "\t\t\t\"Free parking\",\n" +
                                            "\t\t\t\"Free WiFi\",\n" +
                                            "\t\t\t\"Non-smoking rooms\",\n" +
                                            "\t\t\t\"Concierge\",\n" +
                                            "\t\t\t\"On-site restaurant\",\n" +
                                            "\t\t\t\"Fitness center\",\n" +
                                            "\t\t\t\"Pet-friendly rooms\",\n" +
                                            "\t\t\t\"Room service\",\n" +
                                            "\t\t\t\"Business center\",\n" +
                                            "\t\t\t\"Meeting rooms\"\n" +
                                            "\t\t]\n"
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Amenities added successfully")
    @ApiResponse(responseCode = "404", description = "Hotel not found")
    @PostMapping("/{id}/amenities")
    public ResponseEntity<Void> addAmenitiesToHotel(@Parameter(description = "Hotel ID",
            example = "1")
                                                    @PathVariable Long id,
                                                    @RequestBody List<String> amenities) {
        service.addAmenities(id, amenities);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get hotels histogram", description = "Get the number of hotels grouped by a specified parameter")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Object.class),
                    examples = @ExampleObject(
                            name = "HistogramExample",
                            value = "{\n" +
                                    "  \"Minsk\": 1\n" +
                                    "}"
                    )
            )
    )
    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHotelsHistogram(@Parameter(description = "Group by",
            example = "city")
                                                                @PathVariable String param) {
        Map<String, Long> histogram = service.getHotelsHistogram(param);
        return ResponseEntity.ok(histogram);
    }
}

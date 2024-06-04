package com.gpsolutions.util.strategy.factory;

import com.gpsolutions.dao.HotelRepository;
import com.gpsolutions.util.strategy.HistogramStrategy;
import com.gpsolutions.util.strategy.impl.AmenitiesStrategy;
import com.gpsolutions.util.strategy.impl.BrandStrategy;
import com.gpsolutions.util.strategy.impl.CityStrategy;
import com.gpsolutions.util.strategy.impl.CountryStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class StrategyFactory {

    private final HotelRepository repository;

    public HistogramStrategy getStrategy(String param) {
        return switch (param) {
            case "brand" -> new BrandStrategy(repository);
            case "city" -> new CityStrategy(repository);
            case "country" -> new CountryStrategy(repository);
            case "amenities" -> new AmenitiesStrategy(repository);
            default -> throw new IllegalArgumentException("Invalid histogram parameter: " + param);
        };
    }
}

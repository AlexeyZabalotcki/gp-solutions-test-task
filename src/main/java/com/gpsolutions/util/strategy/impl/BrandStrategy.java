package com.gpsolutions.util.strategy.impl;

import com.gpsolutions.dao.HotelRepository;
import com.gpsolutions.util.strategy.HistogramStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class BrandStrategy implements HistogramStrategy {

    private final HotelRepository repository;

    @Override
    public Map<String, Long> getHistogram() {
        List<Object[]> results = repository.countHotelsGroupedByBrand();

        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> ((Long) result[1])
                ));
    }
}


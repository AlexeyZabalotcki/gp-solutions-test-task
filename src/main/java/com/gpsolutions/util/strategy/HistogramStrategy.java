package com.gpsolutions.util.strategy;

import java.util.Map;

public interface HistogramStrategy {
    Map<String, Long> getHistogram();
}

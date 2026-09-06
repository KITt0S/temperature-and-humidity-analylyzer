package com.k1ts.app.logs_generator;

import java.time.Instant;

public record SensorData(long timestamp, int temperature, int humidity) {
}

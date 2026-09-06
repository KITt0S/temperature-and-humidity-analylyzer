package com.k1ts.app.analyzer.entity;

public record TemperatureData(long timestamp, double temperature) {

    @Override
    public String toString() {
        return "TemperatureData{" +
                "timestamp=" + timestamp +
                ", temperature=" + temperature +
                '}';
    }
}

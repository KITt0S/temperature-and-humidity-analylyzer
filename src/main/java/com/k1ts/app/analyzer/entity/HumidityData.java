package com.k1ts.app.analyzer.entity;

public record HumidityData(long timestamp, double humidity) {
    @Override
    public String toString() {
        return "HumidityData{" +
                "timestamp=" + timestamp +
                ", humidity=" + humidity +
                '}';
    }
}

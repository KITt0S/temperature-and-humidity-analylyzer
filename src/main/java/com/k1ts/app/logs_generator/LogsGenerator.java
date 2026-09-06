package com.k1ts.app.logs_generator;

public class LogsGenerator {
    public static void main(String[] args) {
        SensorData[] data = new SensorDataGenerator().generate();
        new DbcEncoder().write(data, "data/logs.txt");
    }
}

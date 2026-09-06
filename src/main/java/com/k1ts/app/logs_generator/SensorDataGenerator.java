package com.k1ts.app.logs_generator;

import java.time.Instant;
import java.util.Random;
import java.util.stream.IntStream;

public class SensorDataGenerator {


    public SensorData[] generate() {
        int count = 1000;

        SensorData[] result = new SensorData[count];

        int[] instants = IntStream.range(0, count).toArray();

        for (int i = 0; i < count; i++) {
            int instant = instants[i];
            int temperature = (int) (9000.0 + 9000.0 * Math.sin(2 * Math.PI * 1 / count * instant));
            int humidity = (int) (5000.0 + 5000.0 *  Math.cos(2 * Math.PI * 1 / count * instant));
            result[i] = new SensorData(Instant.now().toEpochMilli() + instant, temperature, humidity);
        }

        return result;
    }
}

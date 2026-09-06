package com.k1ts.app.logs_generator;

import com.k1ts.app.logs_generator.humidity.HumidityEncoder;
import com.k1ts.app.logs_generator.temperature.TemperatureEncoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DbcEncoder {
    private final TemperatureEncoder temperatureEncoder = new TemperatureEncoder();
    private final HumidityEncoder humidityEncoder = new HumidityEncoder();

    public void write(SensorData[] data, String filename) {
        if (!Files.exists(Path.of(filename))) {
            try {
                Files.createDirectories(Path.of(filename).getParent());
                Files.createFile(Path.of(filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (SensorData datum : data) {
                String temperatureLog = temperatureEncoder.prepareTemperatureMessage(datum);
                String humidityLog = humidityEncoder.prepareHumidityMessage(datum);
                writer.write(temperatureLog);
                writer.newLine();
                writer.write(humidityLog);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

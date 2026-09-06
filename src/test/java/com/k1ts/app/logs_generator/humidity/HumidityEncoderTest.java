package com.k1ts.app.logs_generator.humidity;

import com.k1ts.app.logs_generator.SensorData;
import com.k1ts.app.logs_generator.temperature.TemperatureEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HumidityEncoderTest {

    @Test
    void prepareHumidityMessage() {
        String expected = "(1788688819274) Arduino 0xAB#3FFE";
        String message = new TemperatureEncoder().prepareTemperatureMessage(new SensorData(1788688819274L, 32765, 16382));
        Assertions.assertEquals(expected, message);
    }
}
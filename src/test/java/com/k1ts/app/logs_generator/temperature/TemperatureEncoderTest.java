package com.k1ts.app.logs_generator.temperature;


import com.k1ts.app.logs_generator.SensorData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TemperatureEncoderTest {

    @Test
    void prepareTemperatureMessage() {
        String expected = "(1788688819274) Arduino 0xAA#FFFA";
        String message = new TemperatureEncoder().prepareTemperatureMessage(new SensorData(1788688819274L, 32765, 10000));
        Assertions.assertEquals(expected, message);
    }
}
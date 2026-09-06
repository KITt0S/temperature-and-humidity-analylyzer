package com.k1ts.app.analyzer.temperature;

import com.k1ts.app.analyzer.entity.TemperatureData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TemperatureDecoderTest {

    @Test
    void decode() {
        TemperatureData[] temperatureData = new TemperatureDecoder().decode(new String[]{"(1788690027303) Arduino 0xAA#4650"});
        Assertions.assertEquals(1, temperatureData.length);
        String expected = "TemperatureData{timestamp=1788690027303, temperature=9000}";
        Assertions.assertEquals(expected, temperatureData[0].toString());
    }


    @Test
    void payloadToTemperature() {
        int expected = 32765;
        double temperature = new TemperatureDecoder().payloadToTemperature("FFFA");

        Assertions.assertEquals(expected, temperature);
    }
}
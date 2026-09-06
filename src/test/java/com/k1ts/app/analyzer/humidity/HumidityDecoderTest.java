package com.k1ts.app.analyzer.humidity;

import com.k1ts.app.analyzer.entity.HumidityData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumidityDecoderTest {

    @Test
    void decode() {
    }

    @Test
    void payloadToHumidity() {
        double humidity = new HumidityDecoder().payloadToHumidity("FFFA");
        Assertions.assertEquals(163.82, humidity);
    }
}
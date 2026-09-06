package com.k1ts.app.analyzer;

import com.k1ts.app.analyzer.entity.TemperatureData;
import com.k1ts.app.analyzer.entity.HumidityData;
import com.k1ts.app.analyzer.humidity.HumidityAnalyzer;
import com.k1ts.app.analyzer.humidity.HumidityDecoder;
import com.k1ts.app.analyzer.source.Source;
import com.k1ts.app.analyzer.temperature.TemperatureAnalyzer;
import com.k1ts.app.analyzer.temperature.TemperatureDecoder;

public class TemperatureAndHumidityAnalyzer {

    public static void main(String[] args) {
        Source source = new Source("data/logs.txt");
        String[] logs = source.read();

        TemperatureDecoder temperatureDecoder = new TemperatureDecoder();
        TemperatureData[] temperatureData = temperatureDecoder.decode(logs);

        HumidityDecoder humidityDecoder = new HumidityDecoder();
        HumidityData[] humidityData = humidityDecoder.decode(logs);

        TemperatureAnalyzer temperatureAnalyzer = new TemperatureAnalyzer();
        temperatureAnalyzer.plot(temperatureData);

        HumidityAnalyzer humidityAnalyzer = new HumidityAnalyzer();
        humidityAnalyzer.plot(humidityData);
    }
}

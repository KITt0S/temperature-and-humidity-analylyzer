package com.k1ts.app.logs_generator.humidity;

import com.k1ts.app.logs_generator.SensorData;

public class HumidityEncoder {

    public String prepareHumidityMessage(SensorData data) {
        String pattern = "(${timestamp}) ${pc-id} ${can-id}#${payload}";

        return pattern
                .replace("${timestamp}", Long.toString(data.timestamp()))
                .replace("${pc-id}", "Arduino")
                .replace("${can-id}", "0xAB")
                .replace("${payload}", transformTemperatureToPayloadForm(data.humidity()));
    }

    private String transformTemperatureToPayloadForm(int temperature) {
        boolean[][] bitmap = new boolean[2][8]; // 16 bits
        boolean[][] mask = {
                {true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, false, false}
        };

        String binary = Integer.toBinaryString(temperature);
        if (binary.length() < 14) {
            binary = "0".repeat(14 - binary.length()) + binary;
        }

        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                if (mask[i][j]) {
                    bitmap[i][j] = binary.charAt(i * (bitmap[0].length) + j) > '0';
                }
            }
        }

        String payload = "${first-byte}${second-byte}";
        for (int i = 0; i < bitmap.length; i++) {
            int b = 0;
            for (int j = bitmap[0].length - 1, k = 0; j >= 0; j--, k++) {
                if (bitmap[i][j]) {
                    b += fact2(k);
                }
            }
            String hexByte = Integer.toHexString(b).replace("0x", "");
            hexByte = hexByte.length() == 1 ? "0" + hexByte : hexByte;
            payload = payload.replaceFirst("\\$\\{.*?}", hexByte).toUpperCase();
        }

        return payload;
    }

    private int fact2(int k) {
        int result = 1;

        if (k == 0) {
            return result;
        }

        for (int i = 0; i < k; i++) {
            result *= 2;
        }

        return result;
    }
}

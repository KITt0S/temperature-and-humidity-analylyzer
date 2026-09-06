package com.k1ts.app.analyzer.humidity;

import com.k1ts.app.analyzer.entity.HumidityData;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HumidityDecoder {

    public HumidityData[] decode(String[] logs) {
        return Arrays
                .stream(logs)
                .filter(log -> log.matches("\\(\\d+?\\) \\w+? 0xAB#[0-9A-F]{4}"))
                .map(log -> {
                    Pattern pattern = Pattern.compile("\\((\\d+?)\\) (\\w+?) (0xAB)#([0-9A-F]{4})");
                    Matcher matcher = pattern.matcher(log);
                    boolean found = matcher.find();

                    if (!found) {
                        throw new IllegalStateException();
                    }

                    return new HumidityData(Long.parseLong(matcher.group(1)), payloadToHumidity(matcher.group(4)));
                })
                .toArray(HumidityData[]::new);
    }

    double payloadToHumidity(String payload) {
        boolean[][] bitmap = new boolean[2][8]; // 16 bits
        boolean[][] mask = {
                {true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, false, false}
        };

        for (int i = 0, i1 = 0; i < payload.length(); i += 2, i1++) {
            String singleByte = payload.substring(i, i + 2);
            String binary = hexToBin(singleByte);
            for (int j = 0; j < binary.length(); j++) {
                bitmap[i1][j] = binary.charAt(j) > '0' & mask[i1][j];
            }
        }

        String binary = "";

        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                if (mask[i][j]) {
                    binary += bitmap[i][j] ? '1' : '0';
                }
            }
        }

        int result = 0;
        for (int i = binary.length() - 1, k = 0;
             i >= 0;
             i--, k++) {

            if (binary.charAt(i) > '0') {
                result += fact2(k);
            }
        }

        return result * 0.01;
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

    private String hexToBin(String hex) {
        String result = "";

        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);

            result += hexAlphabet.get(c);
        }

        return result;
    }

    private final Map<Character, String> hexAlphabet = Arrays.stream(new Object[][]{
            {'0', "0000"},
            {'1', "0001"},
            {'2', "0010"},
            {'3', "0011"},
            {'4', "0100"},
            {'5', "0101"},
            {'6', "0110"},
            {'7', "0111"},
            {'8', "1000"},
            {'9', "1001"},
            {'A', "1010"},
            {'B', "1011"},
            {'C', "1100"},
            {'D', "1101"},
            {'E', "1110"},
            {'F', "1111"},
    }).collect(Collectors.toMap(row -> (char) row[0], row -> (String) row[1]));
}

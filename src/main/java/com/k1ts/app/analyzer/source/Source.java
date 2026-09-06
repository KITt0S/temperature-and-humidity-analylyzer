package com.k1ts.app.analyzer.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Source {
    private final String filename;

    public Source(String filename) {
        this.filename = filename;
    }

    public String[] read() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Path.of(filename))))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.smirnov.formula1.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderImpl implements FileReader {

    @Override
    public List<String> readFile(String pathway) {

        try {
            return Files.readAllLines(Paths.get(pathway));
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exist");
        }

    }
}

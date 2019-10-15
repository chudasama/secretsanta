package com.github.chudasama.santa.datasource.impl;

import com.github.chudasama.santa.datasource.DataSource;
import com.github.chudasama.santa.datasource.DataSourceException;
import com.github.chudasama.santa.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The CSVDataSource read the csv file and populate the person objects.
 */

public class CSVDataSource implements DataSource {
    public String FILE_PATH = "file.path";
    String filePath;

    @Override
    public String getName() {
        return "csv";
    }

    @Override
    public void init(Map<String, String> config) {
        Objects.requireNonNull(config, "Missing datasource config");
        Objects.requireNonNull(config.get(FILE_PATH), "Missing file.path config");
        filePath = config.get(FILE_PATH);
    }

    @Override
    public Optional<List<Person>> read() throws DataSourceException {
        Optional<List<Person>> peoples;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath))) {
            //skip header
            // we can use csv parser here but i have it to keep it simple for demo.
            bufferedReader.readLine();
            peoples = Optional.of(bufferedReader.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .map(a -> {
                        // csv file separate the different columns with comma
                        String[] data = a.split(",");
                        String name;
                        Optional<List<String>> familyMembers = Optional.empty();
                        Optional<List<String>> santas = Optional.empty();
                        // will handel malformed csv

                        // name
                        if (data.length >= 1) {
                            name = data[0];
                        }

                        // family member
                        if (data.length >= 2) {
                            familyMembers = Optional.of(Arrays.stream(data[1].split(":")).map(String::trim).filter(t -> !t.isEmpty()).collect(Collectors.toList()));
                        }

                        // last santa list .. this is similar to achieve time restriction to avoid same santa for certain period of time.
                        if (data.length >= 3) {
                            santas = Optional.of(Arrays.stream(data[2].split(":")).map(String::trim).filter(t -> !t.isEmpty()).collect(Collectors.toList()));
                        }
                        return new Person(data[0], familyMembers, santas);
                    })
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new DataSourceException(e);
        }
        return peoples;
    }


}

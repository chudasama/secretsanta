package com.github.chudasama.santa.datasource;

import com.github.chudasama.santa.datasource.impl.CSVDataSource;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SPI loader class.
 */
public class DataSourceProvider {

    /**
     * Will Return list of amiable sink.
     * This static call will be replace with SPI to load different impl.
     *
     * @return
     */
    public static Map<String, DataSource> getDataSourceProvider() {
        // TODO read data using SPI
        return Stream.of(new CSVDataSource()).collect(Collectors.toMap(a -> a.getName().toLowerCase(), b -> b));
    }
}

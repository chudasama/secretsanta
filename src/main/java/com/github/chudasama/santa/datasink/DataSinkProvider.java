package com.github.chudasama.santa.datasink;

import com.github.chudasama.santa.datasink.impl.ConsoleDataSink;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SPI loader class.
 */
public class DataSinkProvider {

    /**
     * Will Return list of amiable sink.
     * This static call will be replace with SPI to load different impl.
     *
     * @return
     */
    public static Map<String, DataSink> getDataSinkProvider() {
        // TODO read data using SPI
        return Stream.of(new ConsoleDataSink()).collect(Collectors.toMap(a -> a.getName().toLowerCase(), b -> b));
    }
}

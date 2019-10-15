package com.github.chudasama.santa.service;

import com.github.chudasama.santa.service.impl.SecretSantaAlgoServiceProvider;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceProvider {

    /**
     * Will Return list of amiable alog provider.
     *
     * @return list of alogs impl.
     */
    public static Map<String, AlgoServiceProvider> getDataSinkProvider() {
        // TODO read data using SPI
        return Stream.of(new SecretSantaAlgoServiceProvider()).collect(Collectors.toMap(a -> a.getName().toLowerCase(), b -> b));
    }
}

package com.github.chudasama.santa.service;

import com.github.chudasama.santa.model.Person;
import com.github.chudasama.santa.model.Tuple;

import java.util.List;
import java.util.Optional;

public interface AlgoServiceProvider {
    /**
     * Will return unique name associated alog service.
     *
     * @return
     */
    String getName();

    Optional<List<Tuple<Person, Person>>> process(Optional<List<Person>> input) throws AlgoServiceException;
}

package com.github.chudasama.santa.datasink;

import com.github.chudasama.santa.model.Person;
import com.github.chudasama.santa.model.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The DataSink impl will provides different output method. Like print to console. write to csv file. etc
 */
public interface DataSink {

    /**
     * Will return unique name associated with data sink.
     *
     * @return
     */
    String getName();

    /**
     * initialise data sink based on configuration.
     *
     * @param config
     */
    void init(Map<String, String> config);

    /**
     * Will write the output to respective impl of data sink.
     *
     * @param data
     * @throws DataSinkException throws exception while writing output to data sink.
     */
    void write(Optional<List<Tuple<Person, Person>>> data) throws DataSinkException;
}

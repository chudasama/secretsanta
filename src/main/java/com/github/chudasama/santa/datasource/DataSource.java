package com.github.chudasama.santa.datasource;

import com.github.chudasama.santa.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The data source handel varity of input format. like csv, jdbc, etc.. in order to support such type just provide impl of respective type.
 */
public interface DataSource {

    /**
     * Will return unique name associated with data source.
     *
     * @return
     */
    String getName();

    /**
     * @param config config object specific to data source
     */
    void init(Map<String, String> config);

    /**
     * Read data from data souce.
     *
     * @return list of people
     * @throws DataSourceException throws exception in case of unable to fetch data from underlying source.
     */
    Optional<List<Person>> read() throws DataSourceException;
}

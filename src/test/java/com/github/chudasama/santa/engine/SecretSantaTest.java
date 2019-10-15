package com.github.chudasama.santa.engine;

import com.github.chudasama.santa.datasink.DataSink;
import com.github.chudasama.santa.datasink.DataSinkException;
import com.github.chudasama.santa.datasink.DataSinkProvider;
import com.github.chudasama.santa.datasink.impl.ConsoleDataSink;
import com.github.chudasama.santa.datasource.DataSource;
import com.github.chudasama.santa.datasource.DataSourceException;
import com.github.chudasama.santa.datasource.DataSourceProvider;
import com.github.chudasama.santa.datasource.impl.CSVDataSource;
import com.github.chudasama.santa.model.Person;
import com.github.chudasama.santa.model.Tuple;
import com.github.chudasama.santa.service.AlgoServiceException;
import com.github.chudasama.santa.service.AlgoServiceProvider;
import com.github.chudasama.santa.service.ServiceProvider;
import com.github.chudasama.santa.service.impl.SecretSantaAlgoServiceProvider;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SecretSantaTest {

    @Test
    public void testSecrectSantaForDefaultCase() throws DataSourceException, DataSinkException, AlgoServiceException {

        String args[] = {"csv", "src/test/resources/test.csv", "console"};

        Map<String, String> dataSourceConfig = new HashMap<>();
        dataSourceConfig.put("file.path", args[1]);
        // this can be dynamically populated based on service provider
        DataSource dataSource = DataSourceProvider.getDataSourceProvider().getOrDefault(args[0], new CSVDataSource());
        dataSource.init(dataSourceConfig);
        Optional<List<Person>> lstSantaPlayers = dataSource.read();

        AlgoServiceProvider algoServiceProvider = ServiceProvider.getDataSinkProvider().getOrDefault("secret-santa-algo-1", new SecretSantaAlgoServiceProvider());
        Optional<List<Tuple<Person, Person>>> process = algoServiceProvider.process(lstSantaPlayers);

        // this can be dynamically populated based on service provider
        // we can have multiple data sink like email sink etc
        DataSink dataSink = DataSinkProvider.getDataSinkProvider().getOrDefault(args[2], new ConsoleDataSink());
        dataSink.init(null);
        dataSink.write(process);
    }
}
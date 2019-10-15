package com.github.chudasama.santa.datasink.impl;

import com.github.chudasama.santa.datasink.DataSink;
import com.github.chudasama.santa.datasink.DataSinkException;
import com.github.chudasama.santa.model.Person;
import com.github.chudasama.santa.model.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsoleDataSink implements DataSink {

    @Override
    public String getName() {
        return "console";
    }

    @Override
    public void init(Map<String, String> config) {
        // do any specific initialization required by underlying sink.
    }

    public void write(Optional<List<Tuple<Person, Person>>> data) throws DataSinkException {

        if (data.isPresent()) {
            System.out.println("List of Receiver and Giver");
            data.get().stream().forEach(mysanta -> System.out.println(mysanta.getFirst().getName() + " --Santa--> " + mysanta.getSecond().getName()));
        } else {
            System.out.println("Oops! Unable to generate secret santa combination");
        }

    }
}

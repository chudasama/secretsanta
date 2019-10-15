package com.github.chudasama.santa.service.impl;

import com.github.chudasama.santa.model.Person;
import com.github.chudasama.santa.model.Tuple;
import com.github.chudasama.santa.service.AlgoServiceException;
import com.github.chudasama.santa.service.AlgoServiceProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class SecretSantaAlgoServiceProvider implements AlgoServiceProvider {

    @Override
    public String getName() {
        return "secret-santa-algo-1";
    }

    @Override
    public Optional<List<Tuple<Person, Person>>> process(Optional<List<Person>> input) throws AlgoServiceException {
        if (input.isPresent()) {
            List<Person> people = input.get();
            List<Tuple<Person, Person>> currentSantas = findSanta(new ArrayList<>(people), new ArrayList<>(input.get()), new ArrayList<Tuple<Person, Person>>(), people.size());
            return currentSantas.isEmpty() ? Optional.empty() : Optional.of(currentSantas);
        }
        return Optional.empty();
    }

    private List<Tuple<Person, Person>> findSanta(List<Person> lstPersons, List<Person> lstSanta, List<Tuple<Person, Person>> currentSelection, int totalPersons) {

        // base case when we have to return
        if (lstPersons.isEmpty()) {
            return currentSelection;
        }

        // will help to revert back to previous stage
        // create local list so that during backtracking we will restore from where we left.
        List<Person> lastOfAvailableSanta = new ArrayList<>(lstSanta);
        List<Tuple<Person, Person>> selectedSanata = new ArrayList<>(currentSelection);
        // take the first person from list ..
        // this is imp as we have list according to restriction
        Person current = lstPersons.get(0);
        String name = current.getName();
        // try to get the list of available santa by excluding any criteria
        // this can be externalized to provide any dynamic criteria
        List<Person> avaiableSantaForCurrentPerson = lastOfAvailableSanta.stream().filter(a -> !a.getName().equalsIgnoreCase(name) && !current.getMyRestrction().contains(a.getName())).collect(Collectors.toList());
        // shuffle santa so that we can pick up the 1st and try our combination
        Collections.shuffle(avaiableSantaForCurrentPerson, new Random());

        // it might be case where we dont have any left for current user.
        // most probably the current user and available santa both are same
        if (avaiableSantaForCurrentPerson.size() > 0) {
            // we will travel to the different combination of santa
            for (Person s : avaiableSantaForCurrentPerson) {
                // take the first santa from list
                lastOfAvailableSanta.remove(s);
                // consider potential santa for current person
                Tuple<Person, Person> canBeMySanta = new Tuple<>(current, s);
                selectedSanata.add(canBeMySanta);
                // lets find santa for n-1 family member
                List<Tuple<Person, Person>> santa1 = findSanta(lstPersons.subList(1, lstPersons.size()), lastOfAvailableSanta, selectedSanata, totalPersons);
                // if we get the desired size list then we did it.
                if (santa1.size() == totalPersons) {
                    return santa1;
                }
                // oops potential santa wont meet the required criteria
                System.out.println("Finding different solution for " + canBeMySanta + " will try with " + lastOfAvailableSanta);
                // remove it
                selectedSanata.remove(canBeMySanta);
                // put santa back to available list.
                lastOfAvailableSanta.add(s);
            }
        } else {   // look for other options
            // case 1 no solution exist
            //findSanta(persons.subList(1, persons.size()), avaiableSanta, currntCandidate,totalCount);
            if (selectedSanata.size() == totalPersons) {
                // did not find the required combination so going back one steps and try to fndout any other possible solution
                return currentSelection;
            }
        }

        return selectedSanata;
    }
}

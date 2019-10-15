package com.github.chudasama.santa.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Model object represent person who participate in secret santa family eve.
 */
public class Person {

    /*
    Name of person .. this will be replace with proper id
     */
    private String name;
    // family details .. it will helps to avoid same family member to exchange gift.
    private Optional<List<String>> familyMember = Optional.empty();
    // list of ex-santa's .. later on we can added time constraints so that last x years santa avoided for selection.
    private Optional<List<String>> santas = Optional.empty();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private Set<String> myRestrction = new HashSet<>();

    public String getName() {
        return name;
    }

    public Optional<List<String>> getFamilyMember() {
        return familyMember;
    }

    public Optional<List<String>> getSantas() {
        return santas;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Optional<List<String>> familyMember, Optional<List<String>> santas) {
        this.name = name;
        this.familyMember = familyMember;
        this.santas = santas;
        myRestrction = Stream.of(familyMember, santas).filter(Optional::isPresent).flatMap(a -> a.get().stream()).collect(Collectors.toSet());
    }

    public boolean canMySecretSanta(String name) {
        return Stream.of(familyMember, santas).filter(Optional::isPresent).flatMap(a -> a.get().stream()).noneMatch(p -> p.equalsIgnoreCase(name));
    }

    public Set<String> getMyRestrction() {
        return myRestrction;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", familyMember=" + familyMember +
                ", santas=" + santas +
                ", myRestrction=" + myRestrction +
                '}';
    }
}

package org.example.repository;

import org.example.enums.Gender;
import org.example.model.Person;

import java.util.List;

public interface Repository <Person, Long>{

    Person findById(Long id);

    List<Person> findAll();

    void save(Person t);

    void delete(Person t);

    void update(Person t);

    List<Person> findUniquePersons();

    public List<Person> findByGenderAndLastNameStartingWith();
}

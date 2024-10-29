package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.enums.Gender;
import org.example.model.Person;
import org.example.repository.Repository;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class PersonService {

    private final Repository<Person, Long> personRepository;

    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public void update(Person person) {
        personRepository.update(person);
    }

    public List<Person> findUniquePerson(){
        return personRepository.findUniquePersons();
    }
    public List<Person> findByGenderAndLastNameStartingWith(){
        return personRepository.findByGenderAndLastNameStartingWith();
    }

}

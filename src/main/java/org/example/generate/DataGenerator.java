package org.example.generate;

import org.example.enums.Gender;
import org.example.model.Person;
import org.example.repository.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private final PersonRepository personRepository;
    private final Random random = new Random();

    public DataGenerator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void generateData(int count) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String firstName = generateFirstName();
            String lastName = generateLastName();
            String secondName = generateSecondName();
            LocalDate dateOfBirth = generateRandomDate();
            Gender gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;

            Person person = new Person(firstName, lastName, secondName, dateOfBirth, gender);
            persons.add(person);
        }

        // Пакетная вставка
        personRepository.saveAll(persons);
    }
    public void generateSpecificData(int count) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String firstName = generateFirstName();
            String lastName = "F" + generateRandomString();
            String secondName = generateSecondName();
            LocalDate dateOfBirth = generateRandomDate();
            Gender gender = Gender.MALE;

            Person person = new Person(firstName, lastName, secondName, dateOfBirth, gender);
            persons.add(person);
        }

        // Пакетная вставка
        personRepository.saveAll(persons);
    }

    private String generateRandomString() {
        StringBuilder name = new StringBuilder();
        int length = random.nextInt(5) + 3;
        for (int i = 0; i < length; i++) {
            char letter = (char) ('a' + random.nextInt(26));
            name.append(letter);
        }
        return name.toString();
    }

    private String generateFirstName() {
        String[] firstNames = {"Alexey", "Michail", "Dmitry", "Ksenia", "Alice", "Diana"};
        return firstNames[random.nextInt(firstNames.length)];
    }

    private String generateLastName() {
        String[] lastNames = {"Smith", "Smirnov", "Kruglov", "Medvedeva", "Brown", "Davis"};
        return lastNames[random.nextInt(lastNames.length)];
    }

    private String generateSecondName() {
        String[] secondNames = {"Ivanovich", "Petrovich", "Sidorovich", "Alexandrovich", "Vladimirovich"};
        return secondNames[random.nextInt(secondNames.length)];
    }

    private LocalDate generateRandomDate() {
        return LocalDate.of(random.nextInt(50) + 1950, random.nextInt(12) + 1, random.nextInt(28) + 1);
    }
}

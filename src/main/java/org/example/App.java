package org.example;

import org.example.config.HibernateConfig;
import org.example.enums.Gender;
import org.example.generate.DataGenerator;
import org.example.model.Person;
import org.example.provider.SessionProvider;
import org.example.repository.PersonRepository;
import org.example.service.PersonService;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Необходимо указать параметр запуска.");
            return;
        }

        SessionProvider sessionProvider = new HibernateConfig();

        SessionFactory sessionFactory = sessionProvider.getSessionFactory();

        PersonRepository personRepository = new PersonRepository(sessionFactory);

        PersonService personService = new PersonService(personRepository);

        DataGenerator dataGenerator = new DataGenerator(personRepository);

        int mode = Integer.parseInt(args[0]);

        switch (mode) {
            case 1 -> createPerson(scanner);
            case 2 -> personService.findUniquePerson().forEach(System.out::println);
            case 3 -> dataGenerator.generateData(scanner.nextInt());
            case 4 -> dataGenerator.generateSpecificData(scanner.nextInt());
            case 5 -> findByGenderAndLastName(personRepository, sessionFactory);
        }
    }

    private static void findByGenderAndLastName(PersonRepository personRepository, SessionFactory sessionFactory) {
        try {
            long startTime = System.currentTimeMillis();
            List<Person> persons = personRepository.findByGenderAndLastNameStartingWith();
            long endTime = System.currentTimeMillis();
            System.out.println("Количество найденных записей: " + persons.size());
            System.out.println("Время выполнения выборки: " + (endTime - startTime) + " миллисекунд");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }


    private static Person createPerson(Scanner scanner) {
        Person person = new Person();
        System.out.println("Please, take your name");
        person.setFirstName(scanner.nextLine());
        System.out.println("Take your lastName");
        person.setLastName(scanner.nextLine());
        System.out.println("Take your secondName");
        person.setSecondName(scanner.nextLine());
        System.out.println("Take your birthDate");
        person.setDate(LocalDate.parse(scanner.nextLine()));
        System.out.println("Take your gender");
        if (scanner.nextLine().equalsIgnoreCase("male")) {
            person.setGender(Gender.MALE);
        } else if (scanner.nextLine().equalsIgnoreCase("female")) {
            person.setGender(Gender.FEMALE);
        }
        return person;
    }
}



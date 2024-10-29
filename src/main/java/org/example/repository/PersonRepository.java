package org.example.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.enums.Gender;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@Getter@Setter
@RequiredArgsConstructor
public class PersonRepository implements Repository<Person, Long> {

    private final SessionFactory sessionFactory;

    @Override
    public Person findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Person.class, id);
        }
    }

    @Override
    public List<Person> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Person", Person.class).list();
        }
    }

    @Override
    public void save(Person t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(t);
            tx.commit();
        }
    }

    @Override
    public void delete(Person t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(t);
            tx.commit();
        }
    }

    @Override
    public void update(Person t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(t);
            tx.commit();
        }
    }

    public void saveAll(List<Person> persons) {
        int batchSize = 1000;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            for (int i = 0; i < persons.size(); i++) {
                session.persist(persons.get(i));
                if (i % batchSize == 0 && i > 0) {
                    tx.commit();
                    tx = session.beginTransaction();
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> findByGenderAndLastNameStartingWith() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT id, firstName, lastName, secondName, date, gender, " +
                    "(EXTRACT(YEAR FROM AGE(CURRENT_DATE, date))) as age " +
                    "FROM ptmk.person " +
                    "WHERE lastName LIKE 'F%' AND gender = 'MALE'";

            return session.createNativeQuery(sql)
                    .getResultList();
        }
    }

    public List<Person> findUniquePersons() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT DISTINCT p " +
                            "FROM Person  p " +
                            "ORDER BY p.firstName, p.lastName, p.secondName", Person.class)
                    .getResultList();
        }
    }


}

package org.example.config;

import org.example.model.Person;
import org.example.provider.SessionProvider;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateConfig implements SessionProvider {

    @Override
    public SessionFactory getSessionFactory() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        hibernateProperties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5431/demo");
        hibernateProperties.setProperty("hibernate.connection.username", "alexey");
        hibernateProperties.setProperty("hibernate.connection.password", "alexey");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");

        return new Configuration()
                .setProperties(hibernateProperties)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

    }
}

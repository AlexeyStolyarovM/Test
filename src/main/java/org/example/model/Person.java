package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.Gender;
import org.hibernate.annotations.Formula;
import java.time.LocalDate;


@RequiredArgsConstructor
@ToString
@Getter@Setter
@Entity
@Table(name = "person",schema = "ptmk", indexes = {
        @Index(name = "idx_gender_lastname", columnList = "gender, lastName")
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String secondName;
    @Column
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Formula("(EXTRACT(YEAR FROM AGE(CURRENT_DATE, date)))")
    int age;

    public Person(String firstName, String lastName, String secondName, LocalDate date, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.date = date;
        this.gender = gender;
    }
}

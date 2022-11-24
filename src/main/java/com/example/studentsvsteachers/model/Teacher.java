package com.example.studentsvsteachers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String subject;
    @ManyToMany(mappedBy = "teachers")
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id)
                && Objects.equals(firstName, teacher.firstName)
                && Objects.equals(lastName, teacher.lastName)
                && Objects.equals(age, teacher.age)
                && Objects.equals(email, teacher.email)
                && Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, subject);
    }
}

package com.example.studentsvsteachers.repository;

import com.example.studentsvsteachers.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM students s "
            + "JOIN students_teachers st ON s.id = st.student_id "
            + "JOIN teachers t ON st.teacher_id = t.id WHERE t.id = :teacherId",
            nativeQuery = true)
    List<Student> findAllByTeacherId(Long teacherId);

    List<Student> findAllByFirstNameOrLastName(String firstName, String lastName);

    List<Student> findAllByFirstNameAndLastName(String firstName, String lastName);
}

package com.example.studentsvsteachers.repository;

import com.example.studentsvsteachers.model.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "SELECT * FROM teachers t "
            + "JOIN students_teachers st ON t.id = st.student_id "
            + "JOIN students s ON st.teacher_id = s.id WHERE s.id = :studentId",
            nativeQuery = true)
    List<Teacher> findAllByStudentId(Long studentId);

    List<Teacher> findAllByFirstNameOrLastName(String firstName, String lastName);

    List<Teacher> findAllByFirstNameAndLastName(String firstName, String lastName);
}

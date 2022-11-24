package com.example.studentsvsteachers.service;

import com.example.studentsvsteachers.model.Student;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface StudentService {
    Student create(Student student);

    Student getById(Long id);

    void delete(Long id);

    Student update(Student student);

    List<Student> getAll(PageRequest pageRequest);

    List<Student> getByTeacher(Long teacherId);

    List<Student> getByName(String firstName, String lastName);

    Student addTeacherToStudent(Long studentId, Long teacherId);

    void removeTeacherFromStudent(Long studentId, Long teacherId);
}

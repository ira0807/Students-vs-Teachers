package com.example.studentsvsteachers.service;

import com.example.studentsvsteachers.model.Teacher;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface TeacherService {
    Teacher create(Teacher teacher);

    Teacher getById(Long id);

    void delete(Long id);

    Teacher update(Teacher teacher);

    List<Teacher> getAll(PageRequest pageRequest);

    List<Teacher> getByStudent(Long studentId);

    List<Teacher> getByName(String firstName, String lastName);

    void removeStudentFromTeacher(Long teacherId, Long studentId);
}

package com.example.studentsvsteachers.service.impl;

import com.example.studentsvsteachers.model.Student;
import com.example.studentsvsteachers.model.Teacher;
import com.example.studentsvsteachers.repository.StudentRepository;
import com.example.studentsvsteachers.repository.TeacherRepository;
import com.example.studentsvsteachers.service.TeacherService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAll(PageRequest pageRequest) {
        return teacherRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Teacher> getByStudent(Long studentId) {
        return teacherRepository.findAllByStudentId(studentId);
    }

    @Override
    public List<Teacher> getByName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return teacherRepository.findAllByFirstNameOrLastName(firstName, lastName);
        } else {
            return teacherRepository.findAllByFirstNameAndLastName(firstName, lastName);
        }
    }

    @Override
    public void removeStudentFromTeacher(Long teacherId, Long studentId) {
        Teacher teacher = teacherRepository.getById(teacherId);
        List<Student> students = studentRepository.findAllByTeacherId(teacher.getId());
        students.remove(studentRepository.getById(studentId));
        teacher.setStudents(students);
        teacherRepository.save(teacher);
    }
}

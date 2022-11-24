package com.example.studentsvsteachers.service.impl;

import com.example.studentsvsteachers.model.Student;
import com.example.studentsvsteachers.model.Teacher;
import com.example.studentsvsteachers.repository.StudentRepository;
import com.example.studentsvsteachers.repository.TeacherRepository;
import com.example.studentsvsteachers.service.StudentService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll(PageRequest pageRequest) {
        return studentRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Student> getByTeacher(Long teacherId) {
        return studentRepository.findAllByTeacherId(teacherId);
    }

    @Override
    public List<Student> getByName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            return studentRepository.findAllByFirstNameOrLastName(firstName, lastName);
        } else {
            return studentRepository.findAllByFirstNameAndLastName(firstName, lastName);
        }
    }

    @Override
    public Student addTeacherToStudent(Long studentId, Long teacherId) {
        Student student = studentRepository.getById(studentId);
        Teacher teacher = teacherRepository.getById(teacherId);
        List<Teacher> teachers = student.getTeachers();
        teachers.add(teacher);
        student.setTeachers(teachers);
        studentRepository.save(student);
        return student;
    }

    @Override
    public void removeTeacherFromStudent(Long studentId, Long teacherId) {
        Student student = studentRepository.getById(studentId);
        List<Teacher> teachers = teacherRepository.findAllByStudentId(student.getId());
        teachers.remove(teacherRepository.getById(teacherId));
        student.setTeachers(teachers);
        studentRepository.save(student);
    }
}

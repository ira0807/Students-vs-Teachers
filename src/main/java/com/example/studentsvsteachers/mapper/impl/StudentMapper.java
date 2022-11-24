package com.example.studentsvsteachers.mapper.impl;

import com.example.studentsvsteachers.mapper.Mapper;
import com.example.studentsvsteachers.model.Student;
import com.example.studentsvsteachers.model.Teacher;
import com.example.studentsvsteachers.model.dto.request.StudentRequestDto;
import com.example.studentsvsteachers.model.dto.response.StudentResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<Student, StudentResponseDto, StudentRequestDto> {
    @Override
    public Student toModel(StudentRequestDto requestDto) {
        Student student = new Student();
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());
        student.setAge(requestDto.getAge());
        student.setEmail(requestDto.getEmail());
        student.setMajor(requestDto.getMajor());
        return student;
    }

    @Override
    public StudentResponseDto toDto(Student student) {
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(student.getId());
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setMajor(student.getMajor());
        List<Long> teachersId = student.getTeachers()
                .stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());
        responseDto.setTeachersId(teachersId);
        return responseDto;
    }
}

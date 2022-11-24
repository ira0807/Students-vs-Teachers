package com.example.studentsvsteachers.mapper.impl;

import com.example.studentsvsteachers.mapper.Mapper;
import com.example.studentsvsteachers.model.Student;
import com.example.studentsvsteachers.model.Teacher;
import com.example.studentsvsteachers.model.dto.request.TeacherRequestDto;
import com.example.studentsvsteachers.model.dto.response.TeacherResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements Mapper<Teacher, TeacherResponseDto, TeacherRequestDto> {
    @Override
    public Teacher toModel(TeacherRequestDto requestDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(requestDto.getFirstName());
        teacher.setLastName(requestDto.getLastName());
        teacher.setAge(requestDto.getAge());
        teacher.setEmail(requestDto.getEmail());
        teacher.setSubject(requestDto.getSubject());
        return teacher;
    }

    @Override
    public TeacherResponseDto toDto(Teacher teacher) {
        TeacherResponseDto responseDto = new TeacherResponseDto();
        responseDto.setId(teacher.getId());
        responseDto.setFirstName(teacher.getFirstName());
        responseDto.setLastName(teacher.getLastName());
        responseDto.setAge(teacher.getAge());
        responseDto.setEmail(teacher.getEmail());
        responseDto.setSubject(teacher.getSubject());
        List<Long> studentsId = teacher.getStudents()
                .stream()
                .map(Student::getId)
                .collect(Collectors.toList());
        responseDto.setStudentsId(studentsId);
        return responseDto;
    }
}

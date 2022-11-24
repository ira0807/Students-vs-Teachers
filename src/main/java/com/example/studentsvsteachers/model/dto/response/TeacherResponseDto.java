package com.example.studentsvsteachers.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String subject;
    private List<Long> studentsId;
}

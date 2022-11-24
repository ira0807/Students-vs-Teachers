package com.example.studentsvsteachers.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDto {
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    @Min(value = 18)
    private Integer age;
    @Email
    private String email;
    private String major;
}

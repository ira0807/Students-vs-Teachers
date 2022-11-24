package com.example.studentsvsteachers.model.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequestDto {
    @Size(min = 3)
    @NotBlank(message = "First name should be longer than two letters")
    private String firstName;
    @Size(min = 3)
    @NotBlank(message = "Last name should be longer than two letters")
    private String lastName;
    @Min(value = 18)
    private Integer age;
    @Email
    private String email;
    private String subject;
}

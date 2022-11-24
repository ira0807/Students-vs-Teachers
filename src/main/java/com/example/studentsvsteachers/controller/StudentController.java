package com.example.studentsvsteachers.controller;

import com.example.studentsvsteachers.mapper.Mapper;
import com.example.studentsvsteachers.model.Student;
import com.example.studentsvsteachers.model.dto.request.StudentRequestDto;
import com.example.studentsvsteachers.model.dto.response.StudentResponseDto;
import com.example.studentsvsteachers.service.StudentService;
import com.example.studentsvsteachers.util.PaginationUtils;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final Mapper<Student, StudentResponseDto, StudentRequestDto> mapper;

    public StudentController(StudentService studentService,
                             Mapper<Student, StudentResponseDto, StudentRequestDto> mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Add new student")
    public StudentResponseDto create(@Valid @RequestBody StudentRequestDto requestDto) {
        return mapper.toDto(studentService.create(mapper.toModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete student by id")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update student by id")
    public StudentResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody StudentRequestDto requestDto) {
        Student student = mapper.toModel(requestDto);
        student.setId(id);
        return mapper.toDto(studentService.update(student));
    }

    @GetMapping
    @ApiOperation(value = "Get list of all students with possibility to pagination and sorting")
    public List<StudentResponseDto> findAll(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "5") Integer count,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageRequest = PaginationUtils.parse(page, count, sortBy);
        return studentService.getAll(pageRequest)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/teachers/{id}")
    @ApiOperation(value = "Get list of all students by teacher")
    public List<StudentResponseDto> findAllByTeacher(
            @PathVariable Long id) {
        return studentService.getByTeacher(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get list of students by their first name or last name")
    public List<StudentResponseDto> findAllByName(@RequestParam(required = false) String firstName,
                                                  @RequestParam(required = false)
                                                  String lastName) {
        return studentService.getByName(firstName, lastName)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping ("/{id}/teachers/{teacherId}")
    @ApiOperation(value = "Add teacher by id to student by id")
    public StudentResponseDto addTeacherToStudent(@PathVariable Long id,
                                                  @PathVariable Long teacherId) {
        return mapper.toDto(studentService.addTeacherToStudent(id, teacherId));
    }

    @DeleteMapping("/{id}/teachers/{teacherId}")
    @ApiOperation(value = "Delete teacher by id from student by id")
    void deleteTeacherFromStudent(@PathVariable Long id,
                                  @PathVariable Long teacherId) {
        studentService.removeTeacherFromStudent(id, teacherId);
    }
}

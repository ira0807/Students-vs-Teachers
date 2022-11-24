package com.example.studentsvsteachers.controller;

import com.example.studentsvsteachers.mapper.Mapper;
import com.example.studentsvsteachers.model.Teacher;
import com.example.studentsvsteachers.model.dto.request.TeacherRequestDto;
import com.example.studentsvsteachers.model.dto.response.TeacherResponseDto;
import com.example.studentsvsteachers.service.TeacherService;
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
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final Mapper<Teacher, TeacherResponseDto, TeacherRequestDto> mapper;

    public TeacherController(TeacherService teacherService,
                             Mapper<Teacher, TeacherResponseDto, TeacherRequestDto> mapper) {
        this.teacherService = teacherService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Add new teacher")
    public TeacherResponseDto create(@Valid @RequestBody TeacherRequestDto requestDto) {
        return mapper.toDto(teacherService.create(mapper.toModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete teacher by id")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update teacher by id")
    public TeacherResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody TeacherRequestDto requestDto) {
        Teacher teacher = mapper.toModel(requestDto);
        teacher.setId(id);
        return mapper.toDto(teacherService.update(teacher));
    }

    @GetMapping
    @ApiOperation(value = "Get list of all teachers with possibility to pagination and sorting")
    public List<TeacherResponseDto> findAll(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "5") Integer count,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageRequest = PaginationUtils.parse(page, count, sortBy);
        return teacherService.getAll(pageRequest)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Get list of all teachers by student")
    public List<TeacherResponseDto> findAllByStudent(
            @PathVariable Long id) {
        return teacherService.getByStudent(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get list of all teachers by their first name or last name")
    public List<TeacherResponseDto> findAllByName(@RequestParam(required = false) String firstName,
                                                  @RequestParam(required = false)
                                                  String lastName) {
        return teacherService.getByName(firstName, lastName)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}/students/{studentId}")
    @ApiOperation(value = "Delete student by id from teacher by id")
    void deleteStudentFromTeacher(@PathVariable Long id,
                                  @PathVariable Long studentId) {
        teacherService.removeStudentFromTeacher(id, studentId);
    }
}

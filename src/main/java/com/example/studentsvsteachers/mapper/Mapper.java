package com.example.studentsvsteachers.mapper;

public interface Mapper<M,S,Q> {
    M toModel(Q request);

    S toDto(M model);
}

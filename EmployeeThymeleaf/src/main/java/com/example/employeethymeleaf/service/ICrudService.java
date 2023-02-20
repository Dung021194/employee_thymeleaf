package com.example.employeethymeleaf.service;

import java.util.List;

public interface ICrudService<E> {
    List<E> findAll();

    E findById(Long id);

    void save(E e);

    void deleteById(Long id);
}

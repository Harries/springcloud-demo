package com.er.eureka.provider.service;

import com.er.eureka.provider.model.Student;

import java.util.Collection;

public interface StudentService {
    public Collection findAll();
    public Student findById(long id);
    public void saveOrUpdate(Student student);
    public void deleteById(long id);
}

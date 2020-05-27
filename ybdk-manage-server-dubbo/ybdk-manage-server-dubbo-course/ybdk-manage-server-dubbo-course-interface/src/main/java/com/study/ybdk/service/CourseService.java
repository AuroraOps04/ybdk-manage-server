package com.study.ybdk.service;

import com.study.ybdk.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    void save(Course course);
    void update(Course course);
    void delete(Integer id);
    Course findById(Integer id);
}

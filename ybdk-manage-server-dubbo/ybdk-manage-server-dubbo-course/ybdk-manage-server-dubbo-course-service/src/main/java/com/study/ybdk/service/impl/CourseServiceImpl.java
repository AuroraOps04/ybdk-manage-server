package com.study.ybdk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.ybdk.entity.Course;
import com.study.ybdk.mapper.CourseMapper;
import com.study.ybdk.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(version = "${dubbo.service.version}")
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.selectList(null);
    }

    @Override
    @Transactional
    public void save(Course course) {
        courseMapper.insert(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        courseMapper.updateById(course);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        courseMapper.deleteById(id);
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.selectById(id);
    }
}

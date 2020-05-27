package com.study.ybdk.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.ybdk.entity.Course;
import com.study.ybdk.exception.Assert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseApiService {
    @Reference(version = "${dubbo.service.version}")
    CourseService courseService;

    public List<Course> findAll(){
        return courseService.findAll();
    }
    public void save(Course course){
        courseService.save(course);
    }
    public void update(Course course){
        Assert.isNotNull(course.getId(), "courseId");
        courseService.update(course);
    }
    public void delete(Integer id){
        Assert.isNotNull(id, "courseId");
        courseService.delete(id);
    }
    public Course findById(Integer id){
        Course course = courseService.findById(id);
        if(course == null){
            Assert.paramInvalid("courseId");
        }
        return course;
    }

}

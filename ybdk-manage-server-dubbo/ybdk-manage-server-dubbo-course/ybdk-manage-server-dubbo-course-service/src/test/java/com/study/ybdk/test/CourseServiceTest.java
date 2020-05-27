package com.study.ybdk.test;

import com.study.ybdk.entity.Course;
import com.study.ybdk.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTest {
    @Autowired
    CourseService courseService;

    @Test
    public void testFindAll(){
        System.out.println(courseService.findAll());
    }

    @Test
    public void testSave(){
        Course course = new Course();
        course.setName("test");
        course.setKId(1);
        courseService.save(course);
    }

    @Test
    public void testUpdate(){
        Course course = new Course();
        course.setId(1);
        course.setKId(2);
        courseService.update(course);
    }

    @Test
    public void testFindById(){
        System.out.println(courseService.findById(1));
    }

    @Test
    public void testDeleteById(){
        courseService.delete(1);
    }
}

package com.study.ybdk.controller;

import com.study.ybdk.entity.Course;
import com.study.ybdk.service.CourseApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.EntityResponse;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ybdk/course")
@Api("易班课程管理Api")
public class CourseController {
    private final CourseApiService courseApiService;

    @GetMapping
    @ApiOperation("查找全部课程")
    public List<Course> findAll() {
        return courseApiService.findAll();
    }

    @PostMapping
    @ApiOperation("插入一条课程信息")
    public void save(@RequestBody Course course) {
        courseApiService.save(course);

    }

    @PutMapping("/{id}")
    @ApiOperation("根据课程id更新课程信息")
    @ApiImplicitParam(name = "id", value = "课程id", dataType = "int", required = true, paramType = "path")
    public void update(@PathVariable("id") Integer courseId, @RequestBody Course course) {
        course.setId(courseId);
        courseApiService.update(course);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除课程信息")
    @ApiImplicitParam(name = "id", value = "课程id", dataType = "int", required = true, paramType = "path")
    public void delete(@PathVariable("id") Integer id) {
        courseApiService.delete(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取课程信息")
    @ApiImplicitParam(name = "id", value = "课程id", dataType = "int", required = true, paramType = "path")
    public Course findOne(@PathVariable("id") Integer id){
        return courseApiService.findById(id);
    }
}

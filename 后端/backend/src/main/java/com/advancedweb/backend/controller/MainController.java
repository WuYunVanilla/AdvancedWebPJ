package com.advancedweb.backend.controller;

import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.MindmapRepository;
import com.advancedweb.backend.service.impl.CourseServiceImpl;
import com.advancedweb.backend.service.impl.MindmapServiceImpl;
import com.advancedweb.backend.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private MindmapServiceImpl mindmapService;

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    @RequestMapping("/get/{course_id}")
    public Course get(@PathVariable String course_id) {
        return courseService.findByCourseId(course_id);
    }

    @RequestMapping(value = "/test")
    public String test() {
        Teacher teacher = teacherService.findByName("lizongyi");
        Course course = courseService.findByCourseId("1");
        teacher.teachIn(course);
        teacherService.save(teacher);

        return "test complete";
    }
}

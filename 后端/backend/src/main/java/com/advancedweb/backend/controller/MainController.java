package com.advancedweb.backend.controller;

import com.advancedweb.backend.model.*;
import com.advancedweb.backend.service.impl.CourseServiceImpl;
import com.advancedweb.backend.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    @RequestMapping("/get/{course_id}")
    public Course get(@PathVariable String course_id) {
        return courseService.findByCourseId(course_id);
    }

    @RequestMapping(value = "/teach_in", method = RequestMethod.GET)
    public boolean teach_in(@RequestParam String name, @RequestParam String course_id) {
        Teacher teacher = teacherService.findByName(name);
        Course course = courseService.findByCourseId(course_id);
        if (teacher == null || course == null) {
            return false;
        }
        teacherService.saveTeachIn(name, course_id);
        return true;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Teacher save(@RequestParam String name, @RequestParam String password) {
        if (teacherService.findByName(name) != null) {
            return teacherService.findByName(name);
        } else {
            Teacher temp = new Teacher();
            temp.setName(name);
            temp.setPassword(password);
            teacherService.save(temp);
            return temp;
        }
    }
}

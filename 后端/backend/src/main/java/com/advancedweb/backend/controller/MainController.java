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

    @RequestMapping(value = "/save/{course_id}/{mindmap_id}/{json_string}", method = RequestMethod.GET)
    public Mindmap save(@PathVariable String course_id, @PathVariable String mindmap_id, @PathVariable String json_string) {
//        System.out.println(course_id + " " + mindmap_id + " " + json_string);
        Course course = courseService.findByCourseId(course_id);
        if (course == null) return null;

        Mindmap mindmap = new Mindmap();
        mindmap.setMindmap_id(mindmap_id);
        mindmap.setJson_string(json_string);
        mindmapService.save(mindmap);

        courseService.saveOwn(course_id, mindmap_id);
        return mindmap;
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

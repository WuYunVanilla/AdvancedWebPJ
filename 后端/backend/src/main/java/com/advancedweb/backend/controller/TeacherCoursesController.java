package com.advancedweb.backend.controller;


import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherCoursesController {

    @Autowired
    private TeacherServiceImpl tsl;


    @RequestMapping(value = "/teacher_courses/{user_name}", method = RequestMethod.GET)
    public Course_json[] register(@PathVariable String user_name) {

        Teacher teacher = tsl.findByName(user_name);
        if (teacher == null) {
            return null;
        }

        System.out.println(teacher.getId());
        //得到数据库的课程列表
        Course[] courses = tsl.findCourses(teacher.getId());

        //只提取我们需要的信息，转换为json
        Course_json[] course_jsons = new Course_json[courses.length];
        if (courses.length > 0) {
            for (int i = 0; i < courses.length; i++) {
                course_jsons[i] = new Course_json();
                course_jsons[i].setCourse_id(courses[i].getCourse_id());
                course_jsons[i].setCourse_name(courses[i].getCourse_name());
                course_jsons[i].setCourse_number(courses[i].getCourse_number());
            }
        }

        return course_jsons;
    }
}

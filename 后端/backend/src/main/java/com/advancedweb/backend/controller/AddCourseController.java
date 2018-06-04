package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.service.impl.CourseServiceImpl;
import com.advancedweb.backend.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddCourseController {

    @Autowired
    private CourseServiceImpl csl;

    @Autowired
    private TeacherServiceImpl tsl;


    @RequestMapping(value = "/add_course/{user_name}", method = RequestMethod.POST)
    public Success register(@PathVariable String user_name, @RequestBody Course course) {
        Success s = new Success();
        s.setSuccess(false);

        String name = course.getCourse_name();
        String course_id = course.getCourse_id();

        //首先判断course_id是否已经存在

        Course course_db = csl.findByCourseId(course_id);
        if (course_db != null) {
            return s;
        }

        Teacher teacher =tsl.findByName(user_name);
        if (teacher==null){
            return s;
        }

        //创建新的course
        course.setCourse_number("0");
        csl.save(course);

        Course course_in_db = csl.findByCourseId(course_id);
        //再创建course和teacher的关系
        teacher.teachIn(course_in_db);
        tsl.save(teacher);

        //打印出这时候teacher的课程列表
        System.out.println(teacher.toString());
        s.setSuccess(true);
        return s;
    }
}

package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AddCourseController {

    @Autowired
    private CourseRepository cr;

    @Autowired
    private TeacherRepository tr;


    @RequestMapping(value = "/add_course_teacher/{user_name}", method = RequestMethod.POST)
    public Success add_course(@PathVariable String user_name, @RequestBody Course course) {
        Success s = new Success();
        s.setSuccess(false);

        String name = course.getCourse_name();
        String course_id = course.getCourse_id();

        //首先判断course_id是否已经存在

        Course course_db = cr.findByCourseId(course_id);
        if (course_db != null) {
            return s;
        }

        Teacher teacher =tr.findByName(user_name);
        if (teacher==null){
            return s;
        }

        //创建新的course
        course.setCourse_number("0");
        cr.save(course);

        Course course_in_db = cr.findByCourseId(course_id);
        //再创建course和teacher的关系
        teacher.teachIn(course_in_db);
        tr.save(teacher);

        //打印出这时候teacher的课程列表
        System.out.println(teacher.toString());
        s.setSuccess(true);
        return s;
    }
}

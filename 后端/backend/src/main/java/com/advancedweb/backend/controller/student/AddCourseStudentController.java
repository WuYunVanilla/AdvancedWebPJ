package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AddCourseStudentController {
    @Autowired
    private CourseRepository cr;
    @Autowired
    private StudentRepository sr;

    @RequestMapping(value = "/add_course_student/{user_name}", method = RequestMethod.POST)
    public Success add_course_student(@PathVariable String user_name, @RequestBody Course course_json) {
        Success s = new Success();
        s.setSuccess(false);

        String course_id = course_json.getCourse_id();

        //找到course
        Course course = cr.findByCourseId(course_id);
        if (course == null) {
            return s;
        }

        //找到student
        Student student = sr.findByName(user_name);
        if (student == null) {
            return s;
        }

        //course的选课人数加1
        int number_before  =  Integer.parseInt(course.getCourse_number());
        course.setCourse_number((number_before+1)+"");
        cr.save(course);

        Course course_in_db = cr.findByCourseId(course_id);
        //再创建course和student的关系
        student.studyIn(course_in_db);
        sr.save(student);

        s.setSuccess(true);
        return s;
    }
}

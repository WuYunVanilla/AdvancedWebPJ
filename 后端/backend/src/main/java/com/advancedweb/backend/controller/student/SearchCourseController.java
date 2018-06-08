package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchCourseController {
    @Autowired
    private CourseRepository cr;

    @RequestMapping(value = "/search_course/{course_id}", method = RequestMethod.GET)
    public cour student_courses(@PathVariable String course_id) {

        Course course = cr.findByCourseId(course_id);

        cour c = new cour();

        c.course_name="Unknown";
        if (course != null) {
            c.course_name=course.getCourse_name();
        }

        return c;
    }

    class cour{
        public  String course_name;
    }

}
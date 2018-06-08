package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.controller.teacher.TeacherCoursesController;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchCourseController {
    @Autowired
    private CourseRepository cr;

    @RequestMapping(value = "/search_course", method = RequestMethod.GET)
    public Course_json[] student_courses() {

        Course[] courses = cr.findAllCourse();

        return TeacherCoursesController.getJsonModel(courses);
    }

}
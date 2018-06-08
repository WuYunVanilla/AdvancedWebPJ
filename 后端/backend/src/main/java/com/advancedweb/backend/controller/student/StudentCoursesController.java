package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.controller.teacher.TeacherCoursesController;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCoursesController {

    @Autowired
    private StudentRepository sr;

    @RequestMapping(value = "/student_courses/{user_name}", method = RequestMethod.GET)
    public Course_json[] student_courses(@PathVariable String user_name) {

        Student student = sr.findByName(user_name);
        if (student == null) {
            return null;
        }

        //得到课程列表
        Course[] courses = sr.findCourses(student.getId());


        return TeacherCoursesController.getJsonModel(courses);
    }
}

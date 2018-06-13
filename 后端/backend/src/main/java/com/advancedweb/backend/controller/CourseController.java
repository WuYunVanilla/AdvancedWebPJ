package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Course_json;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Student;
import com.advancedweb.backend.model.Teacher;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.StudentRepository;
import com.advancedweb.backend.repository.TeacherRepository;
import com.advancedweb.backend.service.CourseService;
import com.advancedweb.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add_course_student/{user_name}", method = RequestMethod.POST)
    public Success add_course_student(@PathVariable String user_name, @RequestBody Course course_json) {
        Success s = new Success();
        s.setSuccess(false);

        String course_id = course_json.getCourse_id();

        //找到course
        Course course = courseService.findByCourseId(course_id);
        if (course == null) {
            return s;
        }

        //找到student
        Student student = userService.findStudentByName(user_name);
        if (student == null) {
            return s;
        }

        //...首先判断这个学生是否已经选了这门课

        Boolean ifChosen =false;
        Course[] studentCourses = userService.getStudentCourses(student.getId());
        for (Course studentCourse: studentCourses){
            if(studentCourse.getCourse_id().equals(course_id)){
                ifChosen=true;
                break;
            }
        }

        //学生还未选这门课
        if (!ifChosen) {
            //course的选课人数加1
            int number_before = Integer.parseInt(course.getCourse_number());
            course.setCourse_number((number_before + 1) + "");
            courseService.saveCourse(course);

            Course course_in_db = courseService.findByCourseId(course_id);
            //再创建course和student的关系
            student.studyIn(course_in_db);
            userService.saveStudent(student);

            s.setSuccess(true);
        }

        return s;
    }

    @RequestMapping(value = "/add_course_teacher/{user_name}", method = RequestMethod.POST)
    public Success add_course_teacher(@PathVariable String user_name, @RequestBody Course course) {
        Success s = new Success();
        s.setSuccess(false);

        String course_id = course.getCourse_id();

        //首先判断course_id是否已经存在
        Course course_db = courseService.findByCourseId(course_id);
        if (course_db != null) {
            return s;
        }

        Teacher teacher = userService.findTeacherByName(user_name);
        if (teacher == null){
            return s;
        }

        //创建新的course
        course.setCourse_number("0");
        courseService.saveCourse(course);

        Course course_in_db = courseService.findByCourseId(course_id);
        //再创建course和teacher的关系
        teacher.teachIn(course_in_db);
        userService.saveTeacher(teacher);

        s.setSuccess(true);
        return s;
    }

    @RequestMapping(value = "/search_course", method = RequestMethod.GET)
    public Course_json[] search_course() {
        Course[] courses= courseService.findCourses();

        return getJsonModel(courses);
    }

    @RequestMapping(value = "/student_courses/{user_name}", method = RequestMethod.GET)
    public Course_json[] student_courses(@PathVariable String user_name) {

        Student student = userService.findStudentByName(user_name);
        if (student == null) {
            return null;
        }

        //得到课程列表
        Course[] courses = userService.getStudentCourses(student.getId());

        return getJsonModel(courses);
    }

    @RequestMapping(value = "/teacher_courses/{user_name}", method = RequestMethod.GET)
    public Course_json[] teacher_courses(@PathVariable String user_name) {

        Teacher teacher = userService.findTeacherByName(user_name);
        if (teacher == null) {
            return null;
        }

        //得到数据库的课程列表
        Course[] courses = userService.getTeacherCourses(teacher.getId());

        return getJsonModel(courses);
    }

    public static Course_json[] getJsonModel(Course[] courses ){
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

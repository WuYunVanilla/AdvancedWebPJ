package com.advancedweb.backend.service;

import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Mindmap;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.StudentRepository;
import com.advancedweb.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course findByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course[] findCourses() {
        return courseRepository.findAllCourses();
    }

    public Mindmap[] findMindmaps(long id) {
        return courseRepository.findMindmaps(id);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

}

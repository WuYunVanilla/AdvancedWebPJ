package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Course")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course findByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }
}

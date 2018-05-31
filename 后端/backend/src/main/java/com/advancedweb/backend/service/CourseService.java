package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    Course findByCourseId(String courseId);
    void save(Course course);
}

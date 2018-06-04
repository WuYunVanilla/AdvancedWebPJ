package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface TeacherService {
    Teacher findByName(String name);
    void save(Teacher teacher);
    Course[] findCourses(long teacher_id);

//    void saveTeachIn(String name, String course_id);
}

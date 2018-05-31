package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    Teacher findByName(String name);
    void save(Teacher teacher);
    void saveTeachIn(String name, String course_id);
}

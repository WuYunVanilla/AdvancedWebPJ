package com.advancedweb.backend.service;

import com.advancedweb.backend.model.node.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    Teacher findByName(String name);
    void save(Teacher teacher);
}

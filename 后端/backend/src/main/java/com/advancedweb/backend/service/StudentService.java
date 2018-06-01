package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student findByName(String name);
    void save(Student student);
}

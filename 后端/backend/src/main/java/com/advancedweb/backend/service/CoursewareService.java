package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import org.springframework.stereotype.Service;

@Service
public interface CoursewareService {
    Courseware findByCoursewareName(String courseware_name);
    void save(Courseware courseware);
}

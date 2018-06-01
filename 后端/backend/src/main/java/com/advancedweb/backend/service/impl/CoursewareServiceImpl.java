package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.model.Courseware;
import com.advancedweb.backend.repository.CoursewareRepository;
import com.advancedweb.backend.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Courseware")
public class CoursewareServiceImpl implements CoursewareService {
    @Autowired
    private CoursewareRepository coursewareRepository;

    @Override
    public Courseware findByCoursewareName(String courseware_name) {
        return coursewareRepository.findByCoursewareName(courseware_name);
    }

    @Override
    public void save(Courseware courseware) {
        coursewareRepository.save(courseware);
    }
}

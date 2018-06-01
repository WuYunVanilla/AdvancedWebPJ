package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.repository.AssignmentShortRepository;
import com.advancedweb.backend.service.AssignmentShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Assignment_short")
public class AssignmentShortServiceImpl implements AssignmentShortService{
    @Autowired
    private AssignmentShortRepository assignmentShortRepository;
}

package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.repository.AssignmentMultipleRepository;
import com.advancedweb.backend.service.AssignmentMultipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Assignment_multiple")
public class AssignmentMultipleServiceImpl implements AssignmentMultipleService {
    @Autowired
    private AssignmentMultipleRepository assignmentMultipleRepository;
}

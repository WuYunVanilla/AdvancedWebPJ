package com.advancedweb.backend.service.impl;

import com.advancedweb.backend.repository.AnswerRepository;
import com.advancedweb.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Answer")
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
}

package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.AssignmentShort_json;
import com.advancedweb.backend.model.AssignmentShort;
import com.advancedweb.backend.repository.AssignmentShortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ShortsController {
    @Autowired
    private AssignmentShortRepository asr;


    @RequestMapping(value = "/shorts/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<AssignmentShort_json> release_multiple(
            @PathVariable String course_id, @PathVariable String mindmap_id, @PathVariable String node_id) {

        String short_id =course_id+" "+mindmap_id+" "+node_id;
        List<AssignmentShort> shorts =asr.findByShortId(short_id);

        List<AssignmentShort_json> short_jsons = new LinkedList<>();

        for (AssignmentShort assignment_short :shorts){
            AssignmentShort_json short_json = new AssignmentShort_json();
            short_json.setTitle(assignment_short.getTitle());
            short_json.setCorrect_answer(assignment_short.getCorrect_answer());
            short_jsons.add(short_json);

        }
        return short_jsons;
    }
}



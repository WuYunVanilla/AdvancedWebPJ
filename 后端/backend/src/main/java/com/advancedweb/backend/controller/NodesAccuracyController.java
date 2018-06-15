package com.advancedweb.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class NodesAccuracyController {
    @RequestMapping(value = "/nodes_accuracy/{course_id}/{mindmap_id}", method = RequestMethod.GET)
    public String nodesAccuracy(@PathVariable String course_id, @PathVariable String mindmap_id) {

    }
}

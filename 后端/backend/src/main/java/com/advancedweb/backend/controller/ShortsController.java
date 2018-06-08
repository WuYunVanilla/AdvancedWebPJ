//package com.advancedweb.backend.controller;
//
//import com.advancedweb.backend.controller.json_model.AssignmentShort_json;
//import com.advancedweb.backend.model.AssignmentShort;
//import com.advancedweb.backend.service.impl.AssignmentShortServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@RestController
//public class ShortsController {
//    @Autowired
//    private AssignmentShortServiceImpl assi;
//
//
//    @RequestMapping(value = "/shorts/{course_id}/{mindmap_id}/{node_name}", method = RequestMethod.GET)
//    public List<AssignmentShort_json> release_multiple(@PathVariable String course_id, @PathVariable String mindmap_id,
//                                                          @PathVariable String node_name) {
//
//        String short_id =course_id+" "+mindmap_id+" "+node_name;
//        List<AssignmentShort> shorts =assi.findByShortId(short_id);
//
//        List<AssignmentShort_json> short_jsons = new LinkedList<>();
//
//        for (AssignmentShort assignment_short :shorts){
//            AssignmentShort_json short_json = new AssignmentShort_json();
//
//            short_json.setTitle(assignment_short.getTitle());
//
//            short_json.setCorrect_answer(assignment_short.getCorrect_answer());
//            short_json.setNumber(assignment_short.getNumber());
//            short_json.setCorrect_number(assignment_short.getCorrect_number());
//
//            short_jsons.add(short_json);
//
//        }
//
//        return short_jsons;
//    }
//}
//
//

package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.AssignmentMultiple_json;
import com.advancedweb.backend.model.AssignmentMultiple;
import com.advancedweb.backend.repository.AssignmentMultipleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class MultiplesController {

    @Autowired
    private AssignmentMultipleRepository amr;


    @RequestMapping(value = "/multiples/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<AssignmentMultiple_json> release_multiple(@PathVariable String course_id, @PathVariable String mindmap_id,
                                                          @PathVariable String node_id) {

        String multi_id =course_id+" "+mindmap_id+" "+node_id;
        List<AssignmentMultiple> multiples =amr.findByMultiId(multi_id);

        List<AssignmentMultiple_json> multiple_jsons = new LinkedList<>();
        for (AssignmentMultiple multiple :multiples){
            AssignmentMultiple_json multiple_json = new AssignmentMultiple_json();

            multiple_json.setTitle(multiple.getTitle());
            multiple_json.setOptionA(multiple.getOptionA());
            multiple_json.setOptionB(multiple.getOptionB());
            multiple_json.setOptionC(multiple.getOptionC());
            multiple_json.setOptionD(multiple.getOptionD());
            multiple_json.setCorrect_answer(multiple.getCorrect_answer());
            multiple_json.setNumber(multiple.getNumber());
            multiple_json.setCorrect_number(multiple.getCorrect_number());

            multiple_jsons.add(multiple_json);

        }

        return multiple_jsons;
    }
}

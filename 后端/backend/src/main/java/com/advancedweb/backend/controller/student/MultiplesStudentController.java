package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.AssignmentMultipleStudent;
import com.advancedweb.backend.controller.json_model.AssignmentMultiple_json;
import com.advancedweb.backend.model.AssignmentMultiple;
import com.advancedweb.backend.repository.AssignmentMultipleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class MultiplesStudentController {
    @Autowired
    private AssignmentMultipleRepository amr;

    @RequestMapping(value = "/multiples_student/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<AssignmentMultipleStudent> multiples_student(@PathVariable String course_id, @PathVariable String mindmap_id,
                                                             @PathVariable String node_id) {

        String multi_id = course_id + " " + mindmap_id + " " + node_id;
        List<AssignmentMultiple> multiples = amr.findByMultiId(multi_id);

        List<AssignmentMultipleStudent> multiples_student = new LinkedList<>();
        for (AssignmentMultiple multiple : multiples) {
            AssignmentMultipleStudent multiple_student = new AssignmentMultipleStudent();

            multiple_student.setTitle(multiple.getTitle());
            multiple_student.setOptionA(multiple.getOptionA());
            multiple_student.setOptionB(multiple.getOptionB());
            multiple_student.setOptionC(multiple.getOptionC());
            multiple_student.setOptionD(multiple.getOptionD());
            multiple_student.setAnswer("");

            multiples_student.add(multiple_student);

        }

        return multiples_student;
    }
}

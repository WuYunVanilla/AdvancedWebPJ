//package com.advancedweb.backend.controller.teacher;
//
//import com.advancedweb.backend.controller.json_model.AssignmentMultiple_json;
//import com.advancedweb.backend.model.AssignmentMultiple;
//import com.advancedweb.backend.repository.AssignmentMultipleRepository;
//import com.advancedweb.backend.service.impl.AssignmentMultipleServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@RestController
//@CrossOrigin
//public class MultiplesController {
//
//    @Autowired
//    private AssignmentMultipleRepository amr;
//
//
//    @RequestMapping(value = "/multiples/{course_id}/{mindmap_id}/{node_name}", method = RequestMethod.GET)
//    public List<AssignmentMultiple_json> release_multiple(@PathVariable String course_id, @PathVariable String mindmap_id,
//                                                          @PathVariable String node_name) {
//
//        String multi_id =course_id+" "+mindmap_id+" "+node_name;
//        List<AssignmentMultiple> multiples =amsi.findByMultiId(multi_id);
//
//        List<AssignmentMultiple_json> multiple_jsons = new LinkedList<>();
//        for (AssignmentMultiple multiple :multiples){
//            AssignmentMultiple_json multiple_json = new AssignmentMultiple_json();
//
//            multiple_json.setTitle(multiple.getTitle());
//            multiple_json.setOptionA(multiple.getOptionA());
//            multiple_json.setOptionB(multiple.getOptionB());
//            multiple_json.setOptionC(multiple.getOptionC());
//            multiple_json.setOptionD(multiple.getOptionD());
//            multiple_json.setCorrect_answer(multiple.getCorrect_answer());
//            multiple_json.setNumber(multiple.getNumber());
//            multiple_json.setCorrect_number(multiple.getCorrect_number());
//
//            multiple_jsons.add(multiple_json);
//
//        }
//
//        return multiple_jsons;
//    }
//}

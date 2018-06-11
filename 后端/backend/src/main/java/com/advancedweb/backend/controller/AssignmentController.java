package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.*;
import com.advancedweb.backend.model.AssignmentMultiple;
import com.advancedweb.backend.model.AssignmentShort;
import com.advancedweb.backend.model.Link;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.AssignmentMultipleRepository;
import com.advancedweb.backend.repository.AssignmentShortRepository;
import com.advancedweb.backend.repository.LinkRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class AssignmentController {
    @Autowired
    private AssignmentShortRepository asr;
    @Autowired
    private AssignmentMultipleRepository amr;
    @Autowired
    private NodeRepository nr;
    @Autowired
    private LinkRepository lr;

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

    @RequestMapping(value = "/answer_multiple/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success answer_multiple(@PathVariable String course_id, @PathVariable String mindmap_id, @PathVariable String node_id,
                                   @RequestBody StudentAnswer stu_ans) {

        Success s = new Success();
        s.setSuccess(false);

        //找到multiple
        List<AssignmentMultiple> multiples = amr.findByMultiId(course_id+" "+mindmap_id+" "+node_id);

        AssignmentMultiple multiple_result=null;
        for (AssignmentMultiple multiple :multiples) {
            if (multiple.getTitle().equals(stu_ans.getTitle())){
                multiple_result =multiple;
                break;
            }
        }


        //比对答案
        if(multiple_result!=null){

            int number_before = Integer.parseInt(multiple_result.getNumber());
            int correct_number_before =Integer.parseInt(multiple_result.getCorrect_number());


            multiple_result.setNumber((number_before+1)+"");
            if(multiple_result.getCorrect_answer().equals(stu_ans.getAnswer())){
                multiple_result.setCorrect_number(correct_number_before+1+"");
            }

            //保存multiple
            amr.save(multiple_result);
            s.setSuccess(true);
        }
        return s;
    }

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

    @RequestMapping(value = "/multiples_teacher/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public List<AssignmentMultiple_json> multiples_teacher(@PathVariable String course_id, @PathVariable String mindmap_id,
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

    @RequestMapping(value = "/release_multiple/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success release_multiple(@PathVariable String course_id, @PathVariable String mindmap_id,
                                    @PathVariable String node_id, @RequestBody AssignmentMultiple multiple) {

        Success success = new Success();
        success.setSuccess(false);


        //找到node
        Node result_node = nr.findByNodeId(course_id+" "+mindmap_id,node_id);

        //向node节点添加HAS_ASSIGNMENT_MULTI关系
        if (result_node != null) {

            //向节点里增加multi_id number correct_number值
            multiple.setMulti_id(course_id + " " + mindmap_id + " " + node_id);
            multiple.setNumber("0");
            multiple.setCorrect_number("0");

            //增加节点
            amr.save(multiple);
            //建立关系
            result_node.setAssignmentMultiple(multiple);
            nr.save(result_node);
            success.setSuccess(true);

        }
        return success;
    }

    @RequestMapping(value = "/release_short/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success release_short(@PathVariable String course_id, @PathVariable String mindmap_id, @PathVariable String node_id, @RequestBody AssignmentShort assignmentShort) {

        Success success = new Success();
        success.setSuccess(false);

        //找到node
        Node result_node = nr.findByNodeId(course_id+" "+mindmap_id,node_id);

        //向node节点添加HAS_ASSIGNMENT_MULTI关系
        if (result_node != null) {

            //向节点里增加multi_id number correct_number值
            assignmentShort.setShort_id(course_id+" "+mindmap_id+" "+node_id);

            //增加节点
            asr.save(assignmentShort);

            //建立关系
            result_node.setAssignmentShorts(assignmentShort);
            nr.save(result_node);
            success.setSuccess(true);

        }
        return success;
    }

    @RequestMapping(value = "/upload_link/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success upload_link(@PathVariable String course_id, @PathVariable String mindmap_id,
                               @PathVariable String node_id,
                               @RequestBody Link link) {

        Success s = new Success();
        s.setSuccess(false);


        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);

        if (result_node != null) {

            //新建Courseware

            lr.save(link);

            //建立关系
            result_node.setLink(link);
            nr.save(result_node);
            s.setSuccess(true);
        }
        return s;
    }

}

package com.advancedweb.backend.controller.student;

import com.advancedweb.backend.controller.json_model.StudentAnswer;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.AssignmentMultiple;
import com.advancedweb.backend.repository.AssignmentMultipleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AnswerMultipleController {
    @Autowired
    private AssignmentMultipleRepository amr;

    @RequestMapping(value = "/answer_multiple/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success answer_multiple(@PathVariable String course_id,@PathVariable String mindmap_id, @PathVariable String node_id,
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

}
package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.AssignmentMultiple;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.AssignmentMultipleRepository;
import com.advancedweb.backend.repository.NodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReleaseMultipleController {

    @Autowired
    private NodeRepository nr;
    @Autowired
    private AssignmentMultipleRepository amr;


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

}

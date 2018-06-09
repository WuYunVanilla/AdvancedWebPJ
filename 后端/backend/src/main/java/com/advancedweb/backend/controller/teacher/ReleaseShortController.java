package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.AssignmentShortRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReleaseShortController {

    @Autowired
    private NodeRepository nr;
    @Autowired
    private AssignmentShortRepository asr;


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

}

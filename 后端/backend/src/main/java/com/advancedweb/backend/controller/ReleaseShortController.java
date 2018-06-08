//package com.advancedweb.backend.controller;
//
//import com.advancedweb.backend.controller.json_model.Success;
//import com.advancedweb.backend.model.*;
//import com.advancedweb.backend.service.impl.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class ReleaseShortController {
//
//    @Autowired
//    private NodeServiceImpl nsi;
//    @Autowired
//    private AssignmentShortServiceImpl assi;
//
//    private String node_name;
//
//    @RequestMapping(value = "/release_short/{course_id}/{mindmap_id}/{node_name}", method = RequestMethod.POST)
//    public Success release_short(@PathVariable String course_id, @PathVariable String mindmap_id, @PathVariable String node_name, @RequestBody AssignmentShort assignmentShort) {
//
//        this.node_name = node_name;
//        Success success = new Success();
//        success.setSuccess(false);
//
//        //找到node
//        Node result_node = nsi.findByNodeId()
//
//
//
//
//        //向node节点添加HAS_ASSIGNMENT_MULTI关系
//        if (result_node != null) {
//
//            //向节点里增加multi_id number correct_number值
//            assignmentShort.setShort_id(course_id+" "+mindmap_id+" "+node_name);
//            assignmentShort.setNumber("0");
//            assignmentShort.setCorrect_number("0");
//
//            //增加节点
//            assi.save(assignmentShort);
//            //建立关系
//            result_node.setAssignmentShorts(assignmentShort);
//            nsi.save(result_node);
//            success.setSuccess(true);
//
//        }
//        return success;
//    }
//
//}

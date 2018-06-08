//package com.advancedweb.backend.controller;
//
//import com.advancedweb.backend.controller.json_model.Success;
//import com.advancedweb.backend.model.AssignmentMultiple;
//import com.advancedweb.backend.model.Course;
//import com.advancedweb.backend.model.Mindmap;
//import com.advancedweb.backend.model.Node;
//import com.advancedweb.backend.service.impl.AssignmentMultipleServiceImpl;
//import com.advancedweb.backend.service.impl.CourseServiceImpl;
//import com.advancedweb.backend.service.impl.MindmapServiceImpl;
//import com.advancedweb.backend.service.impl.NodeServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin
//public class ReleaseMultipleController {
//
//    @Autowired
//    private CourseServiceImpl csl;
//    @Autowired
//    private MindmapServiceImpl msi;
//    @Autowired
//    private NodeServiceImpl nsi;
//    @Autowired
//    private AssignmentMultipleServiceImpl amsi;
//
//    private String node_name;
//
//    @RequestMapping(value = "/release_multiple/{course_id}/{mindmap_id}/{node_name}", method = RequestMethod.POST)
//    public Success release_multiple(@PathVariable String course_id, @PathVariable String mindmap_id,
//                                @PathVariable String node_name, @RequestBody AssignmentMultiple multiple) {
//
//        this.node_name = node_name;
//        Success success = new Success();
//        success.setSuccess(false);
//
//        //找到course
//        Course course = csl.findByCourseId(course_id);
//
//        Mindmap[] mindmaps = csl.findMindmaps(course.getId());
//
//        //找到mindmap
//        Mindmap mindmap_target = null;
//        for (Mindmap mindmap : mindmaps) {
//            if (mindmap.getMindmap_id().equals(mindmap_id)) {
//                mindmap_target = mindmap;
//                break;
//            }
//        }
//
//
//        //找到node
//        Node result_node = null;
//
//        if (mindmap_target != null) {
//            Node node_root = msi.findRootNode(mindmap_target.getId());
//            List<Node> node_children = nsi.findNodeChildren(node_root.getId());
//            result_node = findResultNode(node_children);
//        }
//
//        //向node节点添加HAS_ASSIGNMENT_MULTI关系
//        if (result_node != null) {
//
//            //向节点里增加multi_id number correct_number值
//            multiple.setMulti_id(course_id+" "+mindmap_id+" "+node_name);
//            multiple.setNumber("0");
//            multiple.setCorrect_number("0");
//
//            //增加节点
//            amsi.save(multiple);
//            //建立关系
//            result_node.setAssignmentMultiple(multiple);
//            nsi.save(result_node);
//            success.setSuccess(true);
//
//        }
//        return success;
//    }
//
//    private Node findResultNode(List<Node> node_children) {
//        for (Node node_child : node_children) {
//            if (node_child.getNode_name().equals(node_name)) {
//                return node_child;
//            } else {
//                List<Node> node_children_children = nsi.findNodeChildren(node_child.getId());
//                Node re = findResultNode(node_children_children);
//                if (re != null) {
//                    return re;
//                }
//            }
//        }
//        return null;
//    }
//}

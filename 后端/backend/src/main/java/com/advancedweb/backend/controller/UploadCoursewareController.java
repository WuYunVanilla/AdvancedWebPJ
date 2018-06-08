//package com.advancedweb.backend.controller;
//
//import com.advancedweb.backend.controller.json_model.Success;
//import com.advancedweb.backend.model.*;
//import com.advancedweb.backend.service.impl.AssignmentMultipleServiceImpl;
//import com.advancedweb.backend.service.impl.CourseServiceImpl;
//import com.advancedweb.backend.service.impl.MindmapServiceImpl;
//import com.advancedweb.backend.service.impl.NodeServiceImpl;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.slf4j.Logger;
//
//import java.io.File;
//import java.util.List;
//
//@RestController
//@CrossOrigin
//public class UploadCoursewareController {
//
//    private static final Logger logger = LoggerFactory.getLogger(UploadCoursewareController.class);
//    private final String filePath = "/Users/wy/Desktop/MindMapFileStorage/";
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
//    @RequestMapping(value = "/upload_courseware/{course_id}/{mindmap_id}/{node_name}", method = RequestMethod.POST)
//    public Success release_multiple(@PathVariable String course_id, @PathVariable String mindmap_id,
//                                    @PathVariable String node_name, @RequestParam(value = "file") MultipartFile file) {
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
//
//        //向node节点添加HAS_ASSIGNMENT_MULTI关系
//        if (result_node != null) {
//
//
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            logger.info("上传的文件名为：" + fileName);
//
//            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            logger.info("上传的后缀名为：" + suffixName);
//
//            File dest = new File(filePath + fileName);
//            // 检测是否存在目录
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }
//            try {
//                file.transferTo(dest);
//                logger.info("上传成功后的文件路径未：" + filePath + fileName);
//                return JsonUtil.getSuccessJsonObject(fileName);
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //新建Courseware
//            Courseware courseware = new Courseware();
//            courseware.setCoursewareName(file.getName());
//            courseware.setStoreAddress();
//            multiple.setMulti_id(course_id + " " + mindmap_id + " " + node_name);
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
//

package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.MindmapIdList;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.*;
import com.advancedweb.backend.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MindmapController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private MindmapService mindmapService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeChildService nodeChildService;

    private Gson gson= new Gson();

    private String course_id;
    private String mindmap_id;

    @RequestMapping(value = "/mindmap/{course_id}/{mindmap_id}", method = RequestMethod.GET)
    public String mindmap(@PathVariable String course_id, @PathVariable String mindmap_id) {
        String json = null;
        //先找到mindmap
        Course course = courseService.findByCourseId(course_id);
        Mindmap[] mindmaps_course = courseService.findMindmaps(course.getId());

        Mindmap result_mindmap = null;
        for (Mindmap mindmap : mindmaps_course) {
            if (mindmap.getMindmap_id().equals(mindmap_id)) {
                result_mindmap = mindmap;
                break;
            }
        }

        if (result_mindmap != null) {
            json = result_mindmap.getJson_string();

        }
        return json;
    }

    @RequestMapping(value = "/mindmap_id_list/{course_id}", method = RequestMethod.GET)
    public MindmapIdList mindmap_id_list(@PathVariable String course_id) {
        //先找到course
        Course course = courseService.findByCourseId(course_id);

        //找OWN关系
        Mindmap[] mindmaps = courseService.findMindmaps(course.getId());
        String[] mindmaps_id = new String[mindmaps.length];
        for (int i = 0; i < mindmaps.length; i++){
            mindmaps_id[i] = mindmaps[i].getMindmap_id();
        }

        MindmapIdList mindmapIdList= new MindmapIdList();
        mindmapIdList.setMindmap_id_list(mindmaps_id);
        return mindmapIdList;
    }

    @RequestMapping(value = "/save_mindmap/{course_id}/{mindmap_id}", method = RequestMethod.POST)
    public Success save_mindmap(@PathVariable String course_id, @PathVariable String mindmap_id, @RequestBody String json_string) {
        this.course_id = course_id;
        this.mindmap_id = mindmap_id;

        Success success = new Success();
        success.setSuccess(false);

        //获得课程
        Course course = courseService.findByCourseId(course_id);
        if (course == null) {
            return success;
        }

        // 需要判断该mindmap是否已经存在
        // 若存在，则做修改，否则新建
        boolean if_exist = false;
        Mindmap tempMindmap = mindmapService.findByMindmapId(mindmap_id);
        if (tempMindmap != null)
            if_exist = true;

        Node root_node = gson.fromJson(json_string, Node.class);

        //向每个node添加course_mindmap属性
        //并且对于已存在的node 将所有和次node有关系的节点都全都链接到新节点上
        root_node.setCourse_mindmap(course_id + " " + mindmap_id);
        root_node = recurseForNode(root_node);

        //存下node_root，其余node会自动生成
        nodeService.save(root_node);

        //保存mindmap
        Mindmap mindmap = new Mindmap();
        mindmap.setJson_string(json_string);
        mindmap.setMindmap_id(mindmap_id);

        //保存两者关系
        mindmap.setRootNode(root_node);
        mindmapService.save(mindmap);

        course.owns(mindmap);
        courseService.save(course);

        // 若已存在，则删除原先的mindmap
        if (if_exist) {
            Node tempRootNode = mindmapService.findRootNode(tempMindmap.getId());
            deleteChildren(tempRootNode);
            nodeService.delete(tempRootNode);
            mindmapService.delete(tempMindmap);
        }

        success.setSuccess(true);
        return success;
    }

    //recursion 递归
    private Node recurseForNode(Node node_root) {
        if (node_root.getChildren() != null) {
            for (Node child : node_root.getChildren()) {

                String course_mindmap = course_id + " " + mindmap_id;

                child.setCourse_mindmap(course_mindmap);
                // 若该节点在数据库中已经存在，则把它的所有子节点全都链接到新节点上
                String nodeId = child.getId();
                Node tempNode = nodeService.findByNodeId(course_mindmap, nodeId);
                if (tempNode != null) {
                    Long id = tempNode.getLong_id();
                    // Courseware
                    Courseware[] coursewares = nodeService.findCoursewares(id);
                    // Link
                    Link[] links = nodeService.findLinks(id);
                    // Material
                    Material[] materials = nodeService.findMaterials(id);
                    // Assignment-Multiple
                    AssignmentMultiple[] assignmentMultiples = nodeService.findAssignmentMultiple(id);
                    // Assignment-Short
                    AssignmentShort[] assignmentShorts = nodeService.findAssignmentShort(id);
                    // delete the origin node
                    nodeService.delete(tempNode);
                    // save the new node
                    nodeService.save(child);

                    if (coursewares.length > 0) {
                        for (Courseware c : coursewares) {
                            String coursewareName = c.getCoursewareName();
                            nodeChildService.deleteCoursewareFather(coursewareName);
                            nodeChildService.createCoursewareFather(coursewareName, course_mindmap, nodeId);
                        }
                    }

                    if (links.length > 0) {
                        for (Link l: links) {
                            String linkAddress = l.getLink_address();
                            nodeChildService.deleteLinkFather(linkAddress);
                            nodeChildService.createLinkFather(linkAddress, course_mindmap, nodeId);
                        }
                    }

                    if (materials.length > 0) {
                        for (Material m:materials) {
                            String materialName = m.getMaterialName();
                            nodeChildService.deleteMaterialFather(materialName);
                            nodeChildService.createMaterialFather(materialName, course_mindmap, nodeId);
                        }
                    }

                    if (assignmentMultiples.length > 0) {
                        for (AssignmentMultiple am:assignmentMultiples) {
                            Long multiId = am.getId();
                            nodeChildService.deleteAssignmentMultiFather(multiId);
                            nodeChildService.createAssignmentMultiFather(multiId, course_mindmap, nodeId);
                        }
                    }

                    if (assignmentShorts.length > 0) {
                        for (AssignmentShort as:assignmentShorts) {
                            Long shortId = as.getId();
                            nodeChildService.deleteAssignmentShortFather(shortId);
                            nodeChildService.createAssignmentShortFather(shortId, course_mindmap, nodeId);
                        }
                    }

                }
                child = recurseForNode(child);
            }
        }
        return node_root;
    }

    private void deleteChildren(Node node_root) {
        if (node_root.getChildren() != null) {
            for (Node child : node_root.getChildren()) {
                nodeService.delete(child);
                deleteChildren(child);
            }
        }
    }
}

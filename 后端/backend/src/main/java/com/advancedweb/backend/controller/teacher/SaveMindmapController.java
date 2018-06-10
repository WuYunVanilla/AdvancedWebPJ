package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
public class SaveMindmapController {
    @Autowired
    private CourseRepository cr;
    @Autowired
    private MindmapRepository mr;
    @Autowired
    private NodeRepository nr;
    @Autowired
    private CoursewareRepository coursewareRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private AssignmentMultipleRepository assignmentMultipleRepository;
    @Autowired
    private AssignmentShortRepository assignmentShortRepository;

    private Gson gson= new Gson();

    private String course_id;
    private String mindmap_id;

    @RequestMapping(value = "/save_mindmap/{course_id}/{mindmap_id}", method = RequestMethod.POST)
    public Success save_mindmap(@PathVariable String course_id, @PathVariable String mindmap_id,@RequestBody String json_string) {
        this.course_id=course_id;
        this.mindmap_id=mindmap_id;

        Success success = new Success();
        success.setSuccess(false);

        //获得课程
        Course course = cr.findByCourseId(course_id);
        if (course == null) {
            return success;
        }

        boolean if_exist = false;
        Mindmap tempMindmap = mr.findByMindmap_id(mindmap_id);
        if (tempMindmap != null)
            if_exist = true;

        /**查找这个课程是否已经有了这个mindmap_id**/
        //1..如果已存在，则需要和之前mindmap的json_string做**比较**，插入或删除节点

        //2..不存在mindmap_id

        Node root_node = gson.fromJson(json_string, Node.class);
        //向每个node添加course_mindmap属性
        root_node.setCourse_mindmap(course_id + " " + mindmap_id);
        root_node = setCourseMindmapForNode(root_node);

        //存下node_root，其余node会自动生成
        nr.save(root_node);

        //保存mindmap
        Mindmap mindmap = new Mindmap();
        mindmap.setJson_string(json_string);
        mindmap.setMindmap_id(mindmap_id);

        //保存两者关系
        mindmap.setRootNode(root_node);
        mr.save(mindmap);

        course.owns(mindmap);
        cr.save(course);

        // 若已存在，则删除原先的mindmap
        if (if_exist) {
            Node tempRootNode = mr.findRootNode(tempMindmap.getId());
            deleteChildren(tempRootNode);
            nr.delete(tempRootNode);

            mr.delete(tempMindmap);
        }

        success.setSuccess(true);
        return success;
    }

    //recursion 递归
    private Node setCourseMindmapForNode(Node node_root) {
        if (node_root.getChildren() != null) {
            for (Node child : node_root.getChildren()) {

                String course_mindmap = course_id + " " + mindmap_id;

                child.setCourse_mindmap(course_mindmap);
                // 若该节点在数据库中已经存在，则把它的所有子节点全都链接到新节点上
                String nodeId = child.getId();
                Node tempNode = nr.findByNodeId(course_mindmap, nodeId);
                if (tempNode != null) {
                    Long id = tempNode.getLong_id();
                    // Courseware
                    Courseware[] coursewares = nr.findCoursewares(id);
                    // Link
                    Link[] links = nr.findLinks(id);
                    // Material
                    Material[] materials = nr.findMaterials(id);
                    // Assignment-Multiple
                    AssignmentMultiple[] assignmentMultiples = nr.findAssignmentMultiple(id);
                    // Assignment-Short
                    AssignmentShort[] assignmentShorts = nr.findAssignmentShort(id);
                    // delete the origin node
                    nr.delete(tempNode);
                    // save the new node
                    nr.save(child);

                    if (coursewares.length > 0) {
                        for (Courseware c : coursewares) {
                            String coursewareName = c.getCoursewareName();
                            coursewareRepository.deleteFather(coursewareName);
                            coursewareRepository.createFather(coursewareName, course_mindmap, nodeId);
                        }
                    }

                    if (links.length > 0) {
                        for (Link l: links) {
                            String linkAddress = l.getLink_address();
                            linkRepository.deleteFather(linkAddress);
                            linkRepository.createFather(linkAddress, course_mindmap, nodeId);
                        }
                    }

                    if (materials.length > 0) {
                        for (Material m:materials) {
                            String materialName = m.getMaterialName();
                            materialRepository.deleteFather(materialName);
                            materialRepository.createFather(materialName, course_mindmap, nodeId);
                        }
                    }

                    if (assignmentMultiples.length > 0) {
                        for (AssignmentMultiple am:assignmentMultiples) {
                            Long multiId = am.getId();
                            assignmentMultipleRepository.deleteFather(multiId);
                            assignmentMultipleRepository.createFather(multiId, course_mindmap, nodeId);
                        }
                    }

                    if (assignmentShorts.length > 0) {
                        for (AssignmentShort as:assignmentShorts) {
                            Long shortId = as.getId();
                            assignmentShortRepository.deleteFather(shortId);
                            assignmentShortRepository.createFather(shortId, course_mindmap, nodeId);
                        }
                    }

                }
                child = setCourseMindmapForNode(child);
            }
        }
        return node_root;
    }


    private void deleteChildren(Node node_root) {
        if (node_root.getChildren() != null) {
            for (Node child : node_root.getChildren()) {
                nr.delete(child);
                deleteChildren(child);
            }
        }
    }
}

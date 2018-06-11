package com.advancedweb.backend.service;

import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeChildService {
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

    public void deleteCoursewareFather(String coursewareName) {
        coursewareRepository.deleteFather(coursewareName);
    }

    public void createCoursewareFather(String coursewareName, String course_mindmap, String nodeId) {
        coursewareRepository.createFather(coursewareName, course_mindmap, nodeId);
    }

    public void deleteLinkFather(String linkAddress) {
        linkRepository.deleteFather(linkAddress);
    }

    public void createLinkFather(String linkAddress, String course_mindmap, String nodeId) {
        linkRepository.createFather(linkAddress, course_mindmap, nodeId);
    }

    public void deleteMaterialFather(String materialName) {
        materialRepository.deleteFather(materialName);
    }

    public void createMaterialFather(String materialName, String course_mindmap, String nodeId) {
        materialRepository.createFather(materialName, course_mindmap, nodeId);
    }

    public void deleteAssignmentMultiFather(long id) {
        assignmentMultipleRepository.deleteFather(id);
    }

    public void createAssignmentMultiFather(long id, String course_mindmap, String nodeId) {
        assignmentMultipleRepository.createFather(id, course_mindmap, nodeId);
    }

    public void deleteAssignmentShortFather(long id) {
        assignmentShortRepository.deleteFather(id);
    }

    public void createAssignmentShortFather(long id, String course_mindmap, String nodeId) {
        assignmentShortRepository.createFather(id, course_mindmap, nodeId);
    }

    public List<AssignmentShort> findShorts(String shortId) {
        return assignmentShortRepository.findByShortId(shortId);
    }

    public List<AssignmentMultiple> findMultis(String multiId) {
        return assignmentMultipleRepository.findByMultiId(multiId);
    }

    public void saveMulti(AssignmentMultiple assignmentMultiple) {
        assignmentMultipleRepository.save(assignmentMultiple);
    }

    public void saveShort(AssignmentShort assignmentShort) {
        assignmentShortRepository.save(assignmentShort);
    }

    public void saveCourseware(Courseware courseware) {
        coursewareRepository.save(courseware);
    }

    public void saveMaterial(Material material) {
        materialRepository.save(material);
    }

    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    public Courseware findCourseware(String storeAddress) {
        return coursewareRepository.findByStoreAddress(storeAddress);
    }

    public Material findMaterial(String storeAddress) {
        return materialRepository.findByStoreAddress(storeAddress);
    }
}

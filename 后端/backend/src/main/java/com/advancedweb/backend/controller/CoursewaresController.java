package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Courseware;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.CoursewareRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin
public class CoursewaresController {

    @Autowired
    private NodeRepository nr;

    @RequestMapping(value = "/coursewares/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public CoursewareName[] coursewares(@PathVariable String course_id, @PathVariable String mindmap_id,
                                     @PathVariable String node_id) {

        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);
        Courseware[] coursewares = nr.findCoursewares(result_node.getLong_id());


        CoursewareName[] coursewareNames= new CoursewareName[coursewares.length];
        for (int i=0;i<coursewares.length;i++){
            coursewareNames[i] = new CoursewareName();
            coursewareNames[i].setCourseware_name(coursewares[i].getCoursewareName());
        }

        return coursewareNames;
    }


    class CoursewareName {
        private String courseware_name;

        public String getCourseware_name() {
            return courseware_name;
        }

        public void setCourseware_name(String courseware_name) {
            this.courseware_name = courseware_name;
        }
    }

}



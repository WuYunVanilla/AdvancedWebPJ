package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.CoursewareName;
import com.advancedweb.backend.model.Courseware;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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

}



package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Data;
import com.advancedweb.backend.controller.json_model.Node_json;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Mindmap;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.MindmapRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MindmapController {
    @Autowired
    private MindmapRepository mr;

    @Autowired
    private CourseRepository cr;

    @Autowired
    private NodeRepository nr;

    @RequestMapping(value = "/mindmap/{course_id}/{mindmap_id}", method = RequestMethod.GET)
    public Data mindmap(@PathVariable String course_id, @PathVariable String mindmap_id) {
        Data data = new Data();

        //先找到mindmap
        Course course = cr.findByCourseId(course_id);
        Mindmap[] mindmaps_course = cr.findMindmaps(course.getId());

        Mindmap result_mindmap = null;
        for (Mindmap mindmap : mindmaps_course) {
            if (mindmap.getMindmap_id().equals(mindmap_id)) {
                result_mindmap = mindmap;
                break;
            }
        }

        if (result_mindmap != null) {
            data.setData(result_mindmap.getJson_string());
        }
        return data;
    }

}

package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.MindmapIdList;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Mindmap;
import com.advancedweb.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MindmapIdListController {
    @Autowired
    private CourseRepository cr;

    @RequestMapping(value = "/mindmap_id_list/{course_id}", method = RequestMethod.GET)
    public MindmapIdList mindmap_id_list(@PathVariable String course_id) {
        //先找到course
        Course course = cr.findByCourseId(course_id);

        //找OWN关系
        Mindmap[] mindmaps = cr.findMindmaps(course.getId());
        String[] mindmaps_id = new String[mindmaps.length];
        for (int i =0;i<mindmaps.length;i++){
            mindmaps_id[i] = mindmaps[i].getMindmap_id();
        }


        MindmapIdList mindmapIdList= new MindmapIdList();
        mindmapIdList.setMindmap_id_list(mindmaps_id);
        return mindmapIdList;
    }
}

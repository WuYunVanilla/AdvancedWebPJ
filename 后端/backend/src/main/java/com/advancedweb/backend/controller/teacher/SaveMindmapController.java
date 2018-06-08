package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Data;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Course;
import com.advancedweb.backend.model.Mindmap;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.CourseRepository;
import com.advancedweb.backend.repository.MindmapRepository;
import com.advancedweb.backend.repository.NodeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaveMindmapController {
    @Autowired
    private CourseRepository cr;
    @Autowired
    private MindmapRepository mr;
    @Autowired
    private NodeRepository nr;

    private Gson gson= new Gson();

    private String course_id;
    private String mindmap_id;

    @RequestMapping(value = "/save_mindmap/{course_id}/{mindmap_id}", method = RequestMethod.POST)
    public Success save_mindmap(@PathVariable String course_id, @PathVariable String mindmap_id, @RequestBody Data data) {
        this.course_id=course_id;
        this.mindmap_id=mindmap_id;

        Success success = new Success();
        success.setSuccess(false);

        //获得课程
        Course course = cr.findByCourseId(course_id);
        if (course == null) {
            return success;
        }

        /**查找这个课程是否已经有了这个mindmap_id**/
        //1..如果已存在，则需要和之前mindmap的json_string做**比较**，插入或删除节点


        //2..不存在mindmap_id

        //存下node_root，其余node会自动生成
        Node node_root = gson.fromJson(data.getData(),Node.class);

        //向每个node
        nr.save(node_root);

        //保存mindmap
        Mindmap mindmap= new Mindmap();
        mindmap.setJson_string(data.getData());
        mindmap.setMindmap_id(mindmap_id);

        //保存两者关系
        mindmap.setRootNode(node_root);
        mr.save(mindmap);

        course.owns(mindmap);
        cr.save(course);

        success.setSuccess(true);
        return success;
    }

}

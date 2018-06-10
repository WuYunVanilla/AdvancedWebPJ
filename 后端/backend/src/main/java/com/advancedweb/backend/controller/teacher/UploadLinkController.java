package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Link;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.LinkRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UploadLinkController {
    @Autowired
    private NodeRepository nr;
    @Autowired
    private LinkRepository lr;

    @RequestMapping(value = "/upload_link/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success upload_link(@PathVariable String course_id, @PathVariable String mindmap_id,
                               @PathVariable String node_id,
                               @RequestBody Link link) {

        Success s = new Success();
        s.setSuccess(false);


        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);

        if (result_node != null) {

            //新建Courseware

            lr.save(link);

            //建立关系
            result_node.setLink(link);
            nr.save(result_node);
            s.setSuccess(true);
        }
        return s;
    }

}



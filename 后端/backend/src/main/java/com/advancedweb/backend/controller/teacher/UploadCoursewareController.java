package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.*;
import com.advancedweb.backend.repository.CoursewareRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@CrossOrigin
public class UploadCoursewareController {
    @Autowired
    private NodeRepository nr;
    @Autowired
    private CoursewareRepository cr;

    @RequestMapping(value = "/upload_courseware/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success upload_courseware(@PathVariable String course_id, @PathVariable String mindmap_id,
                                     @PathVariable String node_id, @RequestParam(value = "courseware") MultipartFile file) {

        final String filePath = "/Users/wy/Desktop/MindMapFileStorage/"+course_id+"/"+mindmap_id+"/"+node_id+"/courseware/";
        Success s = new Success();
        s.setSuccess(false);

        // 获取文件名
        String fileName = file.getOriginalFilename();

        //首先判断文件名字是否已经存在
        Courseware cw = cr.findByStoreAddress(filePath+fileName);
        if (cw!=null){
            return s;
        }


        File dest = new File(filePath + fileName);

        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);
        if (result_node != null) {

            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //新建Courseware
            Courseware courseware = new Courseware();
            courseware.setCoursewareName(fileName);
            courseware.setStoreAddress(filePath + fileName);
            cr.save(courseware);

            //建立关系
            result_node.setCourseware(courseware);
            nr.save(result_node);
            s.setSuccess(true);
        }
        return s;
    }

}


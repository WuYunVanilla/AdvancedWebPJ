package com.advancedweb.backend.controller.teacher;

import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Material;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.MaterialRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin
public class UploadMaterialController {
    @Autowired
    private NodeRepository nr;
    @Autowired
    private MaterialRepository mr;

    @RequestMapping(value = "/upload_material/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success upload_material(@PathVariable String course_id, @PathVariable String mindmap_id,
                               @PathVariable String node_id,
                                   @RequestParam(value = "material") MultipartFile file) {

        final String filePath = "/Users/wy/Desktop/MindMapFileStorage/"+course_id+"/"+mindmap_id+"/"+node_id+"/"+"/material/";
        Success s = new Success();
        s.setSuccess(false);

        // 获取文件名
        String fileName = file.getOriginalFilename();

        //首先判断文件名字是否已经存在
        Material material = mr.findByStoreAddress(filePath+fileName);
        if (material!=null){
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
            Material ma = new Material();
            ma.setMaterialName(fileName);
            ma.setStoreAddress(filePath + fileName);
            mr.save(ma);

            //建立关系
            result_node.setMaterial(ma);
            nr.save(result_node);
            s.setSuccess(true);
        }
        return s;
    }

}



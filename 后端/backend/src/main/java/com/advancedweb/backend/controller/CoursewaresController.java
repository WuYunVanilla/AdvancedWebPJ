package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.CoursewareName;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Courseware;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.service.NodeChildService;
import com.advancedweb.backend.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin
public class CoursewaresController {
    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeChildService nodeChildService;

    @RequestMapping(value = "/coursewares/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public String[] coursewares(@PathVariable String course_id, @PathVariable String mindmap_id,
                                        @PathVariable String node_id) {
        //找到node
        Node result_node = nodeService.findByNodeId(course_id + " " + mindmap_id, node_id);
        Courseware[] coursewares = nodeService.findCoursewares(result_node.getLong_id());

        String[] ans = new String[coursewares.length];

        for (int i = 0; i < coursewares.length; i++){
            ans[i] = coursewares[i].getCoursewareName();
        }

        return ans;
    }

    @RequestMapping(value = "/upload_courseware/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success upload_courseware(@PathVariable String course_id, @PathVariable String mindmap_id,
                                     @PathVariable String node_id, @RequestParam(value = "courseware") MultipartFile file) {

        final String filePath = "/home/ubuntu/MindMapFileStorage/" + course_id + "/" + mindmap_id + "/" + node_id + "/courseware/";
        Success s = new Success();
        s.setSuccess(false);

        // 获取文件名
        String fileName = file.getOriginalFilename();

        //首先判断文件名字是否已经存在
        Courseware cw = nodeChildService.findCourseware(filePath + fileName);
        if (cw != null){
            return s;
        }

        File dest = new File(filePath + fileName);

        //找到node
        Node result_node = nodeService.findByNodeId(course_id + " " + mindmap_id, node_id);
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
            nodeChildService.saveCourseware(courseware);

            //建立关系
            result_node.setCourseware(courseware);
            nodeService.save(result_node);
            s.setSuccess(true);
        }
        return s;
    }

    @RequestMapping(value = "/download_courseware/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public String download_courseware(@PathVariable String course_id, @PathVariable String mindmap_id,
                                      @PathVariable String node_id, @RequestBody CoursewareName courseware,
                                      HttpServletRequest request, HttpServletResponse response) {

        final String filePath = "G:/MindMapFileStorage/" + course_id + "/" + mindmap_id + "/" + node_id + "/courseware/";

        String courseware_name = courseware.getCourseware_name();
        String fileUrl = filePath + courseware_name;

        File file = new File(fileUrl);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + courseware_name);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}



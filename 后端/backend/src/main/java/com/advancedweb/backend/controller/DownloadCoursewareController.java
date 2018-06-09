package com.advancedweb.backend.controller;

import com.advancedweb.backend.repository.CoursewareRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownloadCoursewareController {
    @Autowired
    private NodeRepository nr;
    @Autowired
    private CoursewareRepository cr;

    @RequestMapping(value = "/download_courseware/{course_id}/{mindmap_id}/{node_id}/{courseware_name}", method = RequestMethod.GET)
    public String download_courseware(@PathVariable String course_id, @PathVariable String mindmap_id,
                                    @PathVariable String node_id, @PathVariable String courseware_name,
                                    HttpServletRequest request, HttpServletResponse response) {

        final String filePath = "/Users/wy/Desktop/MindMapFileStorage/" + course_id + "/" + mindmap_id + "/" + node_id + "/courseware/";

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
                System.out.println("success");
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



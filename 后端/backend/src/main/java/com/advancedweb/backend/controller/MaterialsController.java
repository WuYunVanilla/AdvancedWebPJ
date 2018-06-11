package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.MaterialName;
import com.advancedweb.backend.controller.json_model.Success;
import com.advancedweb.backend.model.Link;
import com.advancedweb.backend.model.Material;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.MaterialRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin
public class MaterialsController {
    @Autowired
    private NodeRepository nr;
    @Autowired
    private MaterialRepository mr;

    @RequestMapping(value = "/materials/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public String[] materials(@PathVariable String course_id, @PathVariable String mindmap_id,
                                                              @PathVariable String node_id) {

        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);
        Material[] materials = nr.findMaterials(result_node.getLong_id());

//        MaterialName[] materialNames= new MaterialName[materials.length];
        String[] ans = new String[materials.length];

        for (int i=0;i<materials.length;i++){
//            materialNames[i] = new MaterialName();
//            materialNames[i].setMaterial_name(materials[i].getMaterialName());
            ans[i] = materials[i].getMaterialName();
        }

        return ans;
    }

    @RequestMapping(value = "/upload_material/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public Success upload_material(@PathVariable String course_id, @PathVariable String mindmap_id,
                                   @PathVariable String node_id,
                                   @RequestParam(value = "material") MultipartFile file) {

        final String filePath = "G:/MindMapFileStorage/"+course_id+"/"+mindmap_id+"/"+node_id+"/"+"/material/";
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

    @RequestMapping(value = "/download_material/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public String download_material(@PathVariable String course_id, @PathVariable String mindmap_id,
                                    @PathVariable String node_id, @RequestBody MaterialName material,
                                    HttpServletRequest request, HttpServletResponse response) {

        final String filePath = "G:/MindMapFileStorage/" + course_id + "/" + mindmap_id + "/" + node_id + "/material/";
        System.out.println(filePath);

        String material_name = material.getMaterial_name();
        String fileUrl = filePath + material_name;

        File file = new File(fileUrl);
        if (file.exists()) {
            System.out.println("exist!!");
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + material_name);// 设置文件名
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

    @RequestMapping(value = "/links/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public String[] links(@PathVariable String course_id, @PathVariable String mindmap_id,
                          @PathVariable String node_id) {

        //找到node
        Node result_node = nr.findByNodeId(course_id + " " + mindmap_id, node_id);

        Link[] links = nr.findLinks(result_node.getLong_id());

//        LinkAdd[] linksAdd = new LinkAdd[links.length];
        String[] ans = new String[links.length];

        for (int i = 0; i < links.length; i++){
//            linksAdd[i] = new LinkAdd();
//            linksAdd[i].setLink_address(links[i].getLink_address());
            ans[i] = links[i].getLink_address();
        }

        return ans;
    }

}



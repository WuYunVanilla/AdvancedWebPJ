package com.advancedweb.backend.controller;

import com.advancedweb.backend.model.Link;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LinksController {

    @Autowired
    private NodeRepository nr;

    @RequestMapping(value = "/links/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.GET)
    public String[] coursewares(@PathVariable String course_id, @PathVariable String mindmap_id,
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


    private class LinkAdd {
        private String link_address;

        public String getLink_address() {
            return link_address;
        }

        public void setLink_address(String link_address) {
            this.link_address = link_address;
        }
    }

}

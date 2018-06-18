package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.NodesAccuracy;
import com.advancedweb.backend.model.AssignmentMultiple;
import com.advancedweb.backend.model.Mindmap;
import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.service.MindmapService;
import com.advancedweb.backend.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@CrossOrigin
public class NodesAccuracyController {
    @Autowired
    private MindmapService mindmapService;
    @Autowired
    private NodeService nodeService;

    @RequestMapping(value = "/nodes_accuracy/{course_id}/{mindmap_id}", method = RequestMethod.GET)
    public List<NodesAccuracy> nodesAccuracy(@PathVariable String course_id, @PathVariable String mindmap_id) {
        List<NodesAccuracy> nodesAccuracyList = new LinkedList<>();


        //course_id既然没有用 干嘛要传过来 ？
        //获得mindmap
        Mindmap tempMindmap = mindmapService.findByMindmapId(mindmap_id);

        Node root_node = mindmapService.findRootNode(tempMindmap.getId());
        if (root_node == null)
            return nodesAccuracyList;

        //深度遍历
        Queue<Node> nodes = new LinkedList<>();
        Queue<Node> nodesChildren = new LinkedList<>();
        nodes.add(root_node);

        while (!nodes.isEmpty() || !nodesChildren.isEmpty()) {

            if (nodes.isEmpty()) {
                nodes = nodesChildren;
                nodesChildren = new LinkedList<>();
            }
            Node thisNode = nodes.peek();

            //获得答题人数
            int number = 0;
            int correctNumber = 0;

            AssignmentMultiple[] multiples = nodeService.findAssignmentMultiple(thisNode.getLong_id());
            for (AssignmentMultiple mul : multiples) {
                number += Integer.parseInt(mul.getNumber());
                correctNumber += Integer.parseInt(mul.getCorrect_number());
            }

            //加入到nodesAccuracyList中
            NodesAccuracy nodesAccuracy = new NodesAccuracy();
            nodesAccuracy.setNode_topic(thisNode.getTopic());
            nodesAccuracy.setNumber(number + "");
            nodesAccuracy.setCorrect_number(correctNumber + "");

            float acc = 0;
            if (number != 0)
                acc = correctNumber/ number;
            nodesAccuracy.setAccuracy(acc+"");
            nodesAccuracyList.add(nodesAccuracy);

            for (Node child : nodeService.findChildren(thisNode.getLong_id())) {
                nodesChildren.add(child);
            }
            //移除
            nodes.remove();
        }

        return nodesAccuracyList;
    }

}

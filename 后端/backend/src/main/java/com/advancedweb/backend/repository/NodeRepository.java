package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NodeRepository extends Neo4jRepository<Node, Long> {
    @Query("MATCH (n:Node) WHERE n.course_mindmap = ({course_mindmap}) and n.node_id=({node_id}) RETURN n")
    Node findByNodeId(@Param("course_mindmap") String course_mindmap,@Param("node_id") String node_id);

//    @Query("start node = node({id}) match (node)-[:HAS_CHILD]->(nodes) return nodes")
//    List<Node> findNodeChildren(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_COURSEWARE]->(coursewares) return coursewares")
    Courseware[] findCoursewares(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_LINK]->(links) return links")
    Link[] findLinks(@Param("id") long id);


    @Query("start node = node({id}) match (node)-[:HAS_MATERIAL]->(materials) return materials")
    Material[] findMaterials(@Param("id") long id);

}

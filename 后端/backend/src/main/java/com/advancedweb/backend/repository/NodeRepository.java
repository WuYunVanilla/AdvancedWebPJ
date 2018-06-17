package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface NodeRepository extends Neo4jRepository<Node, Long> {
    @Query("MATCH (n:Node) WHERE n.course_mindmap = ({course_mindmap}) and n.node_id=({node_id}) RETURN n")
    Node findByNodeId(@Param("course_mindmap") String course_mindmap,@Param("node_id") String node_id);

    @Query("start node = node({id}) match (node)-[:HAS_COURSEWARE]->(coursewares) return coursewares")
    Courseware[] findCoursewares(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_LINK]->(links) return links")
    Link[] findLinks(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_MATERIAL]->(materials) return materials")
    Material[] findMaterials(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_ASSIGNMENT_MULTI]->(ass_multi) return ass_multi")
    AssignmentMultiple[] findAssignmentMultiple(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_ASSIGNMENT_SHORT]->(ass_short) return ass_short")
    AssignmentShort[] findAssignmentShort(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_CHILD]->(children) return children")
    Node[] findChildren(@Param("id") long id);

    @Query("start node = node({id}) match (node)-[:HAS_NOTE]->(notes) return notes")
    Note[] findNotes(@Param("id") long id);
}

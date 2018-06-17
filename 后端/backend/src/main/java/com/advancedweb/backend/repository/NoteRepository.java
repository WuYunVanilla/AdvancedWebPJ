package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.Node;
import com.advancedweb.backend.model.Note;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface NoteRepository extends Neo4jRepository<Note, Long> {

    @Query("MATCH (n:Node) WHERE n.course_mindmap = ({course_mindmap}) and n.node_id=({node_id}) RETURN n")
    Node findByNodeId(@Param("course_mindmap") String course_mindmap, @Param("node_id") String node_id);
}
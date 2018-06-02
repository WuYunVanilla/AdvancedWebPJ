package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface TeacherRepository extends Neo4jRepository<Teacher, Long> {
    @Query("MATCH (n:Teacher) WHERE n.name = ({name}) RETURN n")
    Teacher findByName(@Param("name") String name);
//    @Query("MATCH (n:Teacher) WHERE n.name = ({name}) " +
//            "MATCH (m:Course) WHERE m.course_id = ({course_id})" +
//            "CREATE (n)-[:TEACH_IN]->(m)")
//    void saveTeachIn(@Param("name") String name, @Param("course_id") String course_id);
}

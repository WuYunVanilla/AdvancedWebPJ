package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends Neo4jRepository<Student, Long> {


    @Query("MATCH (n:Student) WHERE n.name = ({name}) RETURN n")
    Student findByName(@Param("name") String name);
//    @Query("MATCH (n:Student) WHERE n.name = ({name}) " +
//            "MATCH (m:Course) WHERE m.course_id = ({course_id})" +
//            "CREATE (n)-[:STUDY_IN]->(m)")
//    void saveStudyIn(@Param("name") String name, @Param("course_id") String course_id);

    @Query("create (:Student {name: ({name}), password: ({password}) })")
    void add(@Param("name") String name, @Param("password") String password);
}


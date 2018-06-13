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


    @Query("start student = node({student_id}) match (student)-[:STUDY_IN]->(courses) return courses")
    Course[] findCourses(@Param("student_id") long student_id);
}


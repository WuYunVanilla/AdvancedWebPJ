package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository extends Neo4jRepository<Course, Long>{
    @Query("MATCH (n:Course) where n.course_id = ({course_id}) RETURN n")
    Course findByCourseId(@Param("course_id") String courseId);
}

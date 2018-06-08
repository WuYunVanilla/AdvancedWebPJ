package com.advancedweb.backend.repository;

import com.advancedweb.backend.model.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Component
public interface CourseRepository extends Neo4jRepository<Course, Long>{
    @Query("MATCH (n:Course) WHERE n.course_id = ({course_id}) RETURN n")
    Course findByCourseId(@Param("course_id") String course_id);

    @Query("start course = node({id}) match (course)-[:OWN]->(mindmaps) return mindmaps")
    Mindmap[] findMindmaps(@Param("id") long id);

    @Query("MATCH (n:Course) RETURN n")
    Course[] findAllCourse();


//    @Query("MATCH (n:Course) WHERE n.course_id = ({course_id}) " +
//            "MATCH (m:Mindmap) WHERE m.mindmap_id = ({mindmap_id})" +
//            "CREATE (n)-[:OWN]->(m)")
//    void saveOwn(@Param("course_id") String course_id, @Param("mindmap_id") String mindmap_id);
}

package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Assignment_short")
public class AssignmentShort {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "short_id")
    private String short_id;
    @Property(name = "title")
    private String title;
    @Property(name = "correct_answer")
    private String correct_answer;
    @Property(name = "number")
    private String number;
    @Property(name = "correct_number")
    private String correct_number;

    @Relationship(type = "ANSWER_SHEET")
    private Set<Answer> answers;

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswer(Answer answer) {
        if (answers == null) {
            answers = new HashSet<>();
        }
        answers.add(answer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShort_id() {
        return short_id;
    }

    public void setShort_id(String short_id) {
        this.short_id = short_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCorrect_number() {
        return correct_number;
    }

    public void setCorrect_number(String correct_number) {
        this.correct_number = correct_number;
    }
}

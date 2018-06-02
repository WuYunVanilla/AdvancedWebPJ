package com.advancedweb.backend.model;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Assignment_multiple")
public class AssignmentMultiple {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "multi_id")
    private String multi_id;
    @Property(name = "title")
    private String title;
    @Property(name = "optionA")
    private String optionA;
    @Property(name = "optionB")
    private String optionB;
    @Property(name = "optionC")
    private String optionC;
    @Property(name = "optionD")
    private String optionD;
    @Property(name = "correct_answer")
    private String correctAnswer;
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

    public String getMulti_id() {
        return multi_id;
    }

    public void setMulti_id(String multi_id) {
        this.multi_id = multi_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dto;

import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class ExamCourse {
    private Long id;
    private String name;
    private LocalDate dateCreated = LocalDate.now();
    private Long englishLevelId;

    public ExamCourse() {
    }

    public ExamCourse(Long id, String name, Long englishLevelId) {
        this.id = id;
        this.name = name;
        this.englishLevelId = englishLevelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getEnglishLevelId() {
        return englishLevelId;
    }

    public void setEnglishLevelId(Long englishLevelId) {
        this.englishLevelId = englishLevelId;
    }

    @Override
    public String toString() {
        return "ExamCourse{" + "id=" + id + ", name=" + name + ", dateCreated=" + dateCreated + ", englishLevelId=" + englishLevelId + '}';
    }
}

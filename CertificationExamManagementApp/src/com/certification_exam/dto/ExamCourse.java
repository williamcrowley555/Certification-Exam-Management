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
    private Integer month = LocalDate.now().getMonthValue();
    private Integer year = LocalDate.now().getYear();
    private Long englishLevelId;

    public ExamCourse() {
    }

    public ExamCourse(Long id, String name, Integer month, Integer year, Long englishLevelId) {
        this.id = id;
        this.name = name;
        this.month = month;
        this.year = year;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getEnglishLevelId() {
        return englishLevelId;
    }

    public void setEnglishLevelId(Long englishLevelId) {
        this.englishLevelId = englishLevelId;
    }

    @Override
    public String toString() {
        return "ExamCourse{" + "id=" + id + ", name=" + name + ", month=" + month + ", year=" + year + ", englishLevelId=" + englishLevelId + '}';
    }
}

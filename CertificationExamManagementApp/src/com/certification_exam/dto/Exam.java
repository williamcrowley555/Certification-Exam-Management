/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dto;

/**
 *
 * @author HP
 */
public class Exam {
    private Long id;
    private int listeningGrade;
    private int speakingGrade;
    private int readingGrade;
    private int writingGrade;
    private Long examineId;
    private Long examRoomId;

    public Exam() {
    }

    public Exam(Long id, int listeningGrade, int speakingGrade, int readingGrade, int writingGrade, Long examineId, Long examRoomId) {
        this.id = id;
        this.listeningGrade = listeningGrade;
        this.speakingGrade = speakingGrade;
        this.readingGrade = readingGrade;
        this.writingGrade = writingGrade;
        this.examineId = examineId;
        this.examRoomId = examRoomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getListeningGrade() {
        return listeningGrade;
    }

    public void setListeningGrade(int listeningGrade) {
        this.listeningGrade = listeningGrade;
    }

    public int getSpeakingGrade() {
        return speakingGrade;
    }

    public void setSpeakingGrade(int speakingGrade) {
        this.speakingGrade = speakingGrade;
    }

    public int getReadingGrade() {
        return readingGrade;
    }

    public void setReadingGrade(int readingGrade) {
        this.readingGrade = readingGrade;
    }

    public int getWritingGrade() {
        return writingGrade;
    }

    public void setWritingGrade(int writingGrade) {
        this.writingGrade = writingGrade;
    }

    public Long getExamineId() {
        return examineId;
    }

    public void setExamineId(Long examineId) {
        this.examineId = examineId;
    }

    public Long getExamRoomId() {
        return examRoomId;
    }

    public void setExamRoomId(Long examRoomId) {
        this.examRoomId = examRoomId;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", listeningGrade=" + listeningGrade + ", speakingGrade=" + speakingGrade + ", readingGrade=" + readingGrade + ", writingGrade=" + writingGrade + ", examineId=" + examineId + ", examRoomId=" + examRoomId + '}';
    }
    
    
}

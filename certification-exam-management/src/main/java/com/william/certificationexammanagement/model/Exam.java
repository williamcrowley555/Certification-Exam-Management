package com.william.certificationexammanagement.model;

import javax.persistence.*;
import javax.validation.Valid;

@Entity(name = "Exam")
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "listening_grade")
    private int listeningGrade;

    @Column(name = "speaking_grade")
    private int speakingGrade;

    @Column(name = "reading_grade")
    private int readingGrade;

    @Column(name = "writing_grade")
    private int writingGrade;

//    1: has been graded
//    2: not graded yet
    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @ManyToOne
    @JoinColumn(name = "examine_id")
    @Valid
    private Examine examine;

    @ManyToOne
    @JoinColumn(name = "exam_room_id")
    @Valid
    private ExamRoom examRoom;

    public Exam() {
    }

    public Exam(Long id, int listeningGrade, int speakingGrade, int readingGrade, int writingGrade, Integer status, Examine examine, ExamRoom examRoom) {
        this.id = id;
        this.listeningGrade = listeningGrade;
        this.speakingGrade = speakingGrade;
        this.readingGrade = readingGrade;
        this.writingGrade = writingGrade;
        this.status = status;
        this.examine = examine;
        this.examRoom = examRoom;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Examine getExamine() {
        return examine;
    }

    public void setExamine(Examine examine) {
        this.examine = examine;
    }

    public ExamRoom getExamRoom() {
        return examRoom;
    }

    public void setExamRoom(ExamRoom examRoom) {
        this.examRoom = examRoom;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", listeningGrade=" + listeningGrade +
                ", speakingGrade=" + speakingGrade +
                ", readingGrade=" + readingGrade +
                ", writingGrade=" + writingGrade +
                ", status=" + status +
                ", examine=" + examine +
                ", examRoom=" + examRoom +
                '}';
    }
}

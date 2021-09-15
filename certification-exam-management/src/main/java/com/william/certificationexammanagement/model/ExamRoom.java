package com.william.certificationexammanagement.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ExamRoom")
@Table(name = "exam_room",
        uniqueConstraints = {
                @UniqueConstraint(name = "exam_room_name_unique", columnNames = "name")
        })
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Pattern(regexp = "^[\\p{L}A-Za-z ]+$")
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity = 0;

    @Column(name = "exam_date", nullable = false)
    private Date examDate = new Date(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "proctor_id")
    @Valid
    private User proctor;

    @ManyToOne
    @JoinColumn(name = "examiner_id")
    @Valid
    private User examiner;

    @ManyToOne
    @JoinColumn(name = "exam_course_id")
    @Valid
    private ExamCourse examCourse;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exam_room_examine",
            joinColumns = @JoinColumn(name = "exam_room_id"),
            inverseJoinColumns = @JoinColumn(name = "examine_id")
    )
    @OrderBy("lastName ASC")
    private Set<Examine> examines = new HashSet<>();

    public ExamRoom() {
    }

    public ExamRoom(Long id, String name, int quantity, Date examDate, User proctor, User examiner, ExamCourse examCourse, Set<Examine> examines) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.examDate = examDate;
        this.proctor = proctor;
        this.examiner = examiner;
        this.examCourse = examCourse;
        this.examines = examines;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public User getProctor() {
        return proctor;
    }

    public void setProctor(User proctor) {
        this.proctor = proctor;
    }

    public User getExaminer() {
        return examiner;
    }

    public void setExaminer(User examiner) {
        this.examiner = examiner;
    }

    public ExamCourse getExamCourse() {
        return examCourse;
    }

    public void setExamCourse(ExamCourse examCourse) {
        this.examCourse = examCourse;
    }

    public Set<Examine> getExamines() {
        return examines;
    }

    public void setExamines(Set<Examine> examines) {
        this.examines = examines;
    }

    @Override
    public String toString() {
        return "ExamRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", examDate=" + examDate +
                ", proctor=" + proctor +
                ", examiner=" + examiner +
                ", examCourse=" + examCourse +
                '}';
    }
}

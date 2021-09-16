package com.william.certificationexammanagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ExamCourse")
@Table(name = "exam_course",
        uniqueConstraints = {
                @UniqueConstraint(name = "exam_course_name_unique", columnNames = "name")
        })
public class ExamCourse {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name", nullable = false)
        @Pattern(regexp = "^[\\\\p{L}A-Za-z0-9.,\\\\s]+$")
        private String name;

        @Column(name = "month", nullable = false)
        @Min(value = 1)
        @Max(value = 12)
        private Integer month = LocalDate.now().getMonthValue();

        @Column(name = "year", nullable = false)
        @Min(value = 1)
        private Integer year = LocalDate.now().getYear();

        @ManyToOne
        @JoinColumn(name = "english_level_id")
        @Valid
        private EnglishLevel englishLevel;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "exam_course_examine",
                joinColumns = @JoinColumn(name = "exam_course_id"),
                inverseJoinColumns = @JoinColumn(name = "examine_id")
        )
        @OrderBy("lastName ASC")
        private Set<Examine> examines = new HashSet<>();

        public ExamCourse() {
        }

        public ExamCourse(Long id, String name, Integer month, Integer year, EnglishLevel englishLevel, Set<Examine> examines) {
                this.id = id;
                this.name = name;
                this.month = month;
                this.year = year;
                this.englishLevel = englishLevel;
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

        public EnglishLevel getEnglishLevel() {
                return englishLevel;
        }

        public void setEnglishLevel(EnglishLevel englishLevel) {
                this.englishLevel = englishLevel;
        }

        public Set<Examine> getExamines() {
                return examines;
        }

        public void setExamines(Set<Examine> examines) {
                this.examines = examines;
        }

        @Override
        public String toString() {
                return "ExamCourse{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", month=" + month +
                        ", year=" + year +
                        ", englishLevel=" + englishLevel +
                        ", examines=" + examines +
                        '}';
        }
}

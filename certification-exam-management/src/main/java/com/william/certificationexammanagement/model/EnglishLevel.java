package com.william.certificationexammanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "EnglishLevel")
@Table(name = "english_level",
        uniqueConstraints = {
                @UniqueConstraint(name = "english_level_name_unique", columnNames = "name")
})
public class EnglishLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Pattern(regexp = "^[\\p{L}A-Za-z0-9]+$")
    private String name;

    public EnglishLevel() {
    }

    public EnglishLevel(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "EnglishLevel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

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
public class EnglishLevel {
    
    private Long id;
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
        return "EnglishLevel{" + "id=" + id + ", name=" + name + '}';
    }
    
}

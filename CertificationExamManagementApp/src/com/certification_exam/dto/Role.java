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
public class Role {
    private Long id;
    private String name;
    private String normalizedName;

    public Role() {
    }

    public Role(Long id, String name, String normalizedName) {
        this.id = id;
        this.name = name;
        this.normalizedName = normalizedName;
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

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", normalizedName=" + normalizedName + '}';
    }
}

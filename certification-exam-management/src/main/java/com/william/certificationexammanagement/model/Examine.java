package com.william.certificationexammanagement.model;

import com.william.certificationexammanagement.validation.Age;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity(name = "Examine")
@Table(name = "examine",
        uniqueConstraints = {
                @UniqueConstraint(name = "examine_examine_id_unique", columnNames = "examineId"),
                @UniqueConstraint(name = "examine_phone_unique", columnNames = "phone")
        })
public class Examine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5)
    @Column(nullable = false, length = 30)
    private String examineId;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z ]+$", message = "Tên không hợp lệ")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Họ không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z]+$", message = "Họ không hợp lệ")
    private String lastName;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Age
    private LocalDate dob;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Hãy chọn giới tính")
    @Min(value = 0)
    @Max(value = 5)
    private Integer gender = 1;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Địa chỉ không được để trống")
    @Pattern(regexp = "^[\\p{L}A-Za-z0-9.,\\s\\-\\/]+$", message = "Địa chỉ không hợp lệ")
    private String address;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "0{1}\\d{9,10}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Column(name = "status", nullable = false, columnDefinition="tinyint(1) default 1")
    private Boolean status = true;

    public Examine() {
    }

    public Examine(Long id, String examineId, String firstName, String lastName, LocalDate dob, Integer gender, String address, String phone, Boolean status) {
        this.id = id;
        this.examineId = examineId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamineId() {
        return examineId;
    }

    public void setExamineId(String examineId) {
        this.examineId = examineId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Examine{" +
                "id=" + id +
                ", examineId='" + examineId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}

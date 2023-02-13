package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "StudentInfo")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int ID;
    private String name;
    private String section;
    private String schoolName;
}

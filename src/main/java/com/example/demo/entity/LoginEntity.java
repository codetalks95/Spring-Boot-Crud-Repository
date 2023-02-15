package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login_credentials")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @Column(unique = true , nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    private String date;
}

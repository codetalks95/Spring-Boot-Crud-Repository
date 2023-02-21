package com.example.demo.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login_credentials")
@JacksonXmlRootElement(localName = "City")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @JacksonXmlProperty
    private String userName;
    @JacksonXmlProperty
    private String userPassword;
    @JacksonXmlProperty
    private String date;
}

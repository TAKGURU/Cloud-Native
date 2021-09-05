package com.example.crud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data; 


@Entity 
@Data 
public class UserInfo { 
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long id; 
    
    @Column(name="name") 
    private String name; 

    @Column(name="age") 
    private int age; 
}

package com.rahulbabu.journal.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;

@Document
//created getters and setters using lombok
// @Getter 
// @Setter

@Data
public class User {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    // private Date date; //this has date with time
    private LocalDate date;

}
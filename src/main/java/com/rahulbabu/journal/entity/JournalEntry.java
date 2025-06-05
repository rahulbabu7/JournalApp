package com.rahulbabu.journal.entity;

import java.time.LocalDate;
// import java.util.Date;

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
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    // private Date date; //this has date with time
    private LocalDate date;

    //Getters and setters  we are creating it without Lombok

    // public void setId(ObjectId id){
    //     this.id = id;
    // }

    // public ObjectId getId(){
    //     return this.id;
    // }

    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // public String getTitle(){
    //     return this.title;
    // }


    // public void setContent(String content){
    //     this.content = content;
    // }

    // public String getContent(){
    //     return this.content;
    // }


    // public void setDate(LocalDate date){
    //     this.date = date;
    // }

    // public LocalDate getDate(){
    //     return this.date;
    // }
}

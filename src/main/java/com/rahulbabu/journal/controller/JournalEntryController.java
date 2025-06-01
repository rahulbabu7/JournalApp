package com.rahulbabu.journal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.rahulbabu.journal.entity.JournalEntry;
import com.rahulbabu.journal.service.JournalEntryService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    
   

    //connecting controller and service
    @Autowired
    private JournalEntryService journalEntryService;

//Maps HTTP GET requests sent to /journal/all.

    @GetMapping()
    public List<JournalEntry> getAll(){
        return journalEntryService.findAllEntries();
}



    @PostMapping()
    public boolean createEntry(@RequestBody JournalEntry myentry){
        myentry.setDate(LocalDate.now());  //sets the todays date
        journalEntryService.saveEntry(myentry); //calling the method from service class
        
        return true;

    }



   
    @GetMapping("id/{myId}")
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
       return journalEntryService.getJournalById(myId);
       //Then the caller must handle the case where the entry is missing.

    }


    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalbyId(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
      //finding the journal with id
      JournalEntry old = journalEntryService.getJournalById(myId).orElse(null);
      if(old !=null){
        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle(): old.getTitle());
        old.setContent(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getContent(): old.getContent());
        /*
         * Update the old object's fields only if the corresponding value in newEntry is not null and not empty .

Otherwise, keep the old value.
         */
    }



    journalEntryService.updateJournalEntry(old);
      return old;

    }



}

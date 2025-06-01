package com.rahulbabu.journal.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahulbabu.journal.Repository.JournalEntryRepository;
import com.rahulbabu.journal.entity.JournalEntry;

import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {
    
//create entry in database

//dependency injection
@Autowired
private JournalEntryRepository journalEntryRepository; //connecting service and repository


public void saveEntry(JournalEntry journalEntry){
    journalEntryRepository.save(journalEntry); //.save() provided by mongorepository
    //saving the journal entry 

}


//get all journal entries
public List<JournalEntry> findAllEntries(){

    return journalEntryRepository.findAll();
}

public Optional<JournalEntry> getJournalById(ObjectId myId){
    return journalEntryRepository.findById(myId);
    //Then the caller must handle the case where the entry is missing.
    /*
     * Here, you call findById(myId) on the repository.

This returns Optional<JournalEntry> because:

    The entry might exist → Optional contains the JournalEntry.

    The entry might not exist → Optional is empty.
     */
}

public void deleteById(ObjectId myId){
 journalEntryRepository.deleteById(myId);
    
}

public void updateJournalEntry(JournalEntry journalEntry){
    journalEntryRepository.save(journalEntry);
}

}

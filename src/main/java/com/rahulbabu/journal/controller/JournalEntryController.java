package com.rahulbabu.journal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.rahulbabu.journal.entity.JournalEntry;
import com.rahulbabu.journal.service.JournalEntryService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

//     @GetMapping()
//     public List<JournalEntry> getAll(){
//         return journalEntryService.findAllEntries();
// }

  @GetMapping()
    public ResponseEntity<List<JournalEntry>> getAll(){
        List<JournalEntry> all = journalEntryService.findAllEntries();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}

//! custom tupe return 
//   @GetMapping()
//     public ResponseEntity<?> getAll(){
//         List<JournalEntry> all = journalEntryService.findAllEntries();
//         if(all !=null && !all.isEmpty()){
//             return new ResponseEntity<>(all,HttpStatus.OK);
//         }
//         else{
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
// }

    // @PostMapping()
    // public boolean createEntry(@RequestBody JournalEntry myentry){
    //     myentry.setDate(LocalDate.now());  //sets the todays date
    //     journalEntryService.saveEntry(myentry); //calling the method from service class
        
    //     return true;

    // }

     @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry){

        try{
            myentry.setDate(LocalDate.now());  //sets the todays date
            journalEntryService.saveEntry(myentry); //calling the method from service class
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(myentry,HttpStatus.BAD_REQUEST);
        }
        
        

    }



    //! without response code

    // @GetMapping("id/{myId}")
    // public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
    //    return journalEntryService.getJournalById(myId);
    //    //Then the caller must handle the case where the entry is missing.

    // }

     @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalById(myId);
       //Then the caller must handle the case where the entry is missing.

       //!with if else
    //    if(journalEntry.isPresent()){
    //     return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
    //    }
    //    else {
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //    }
        /*
         * map is a method on Optional.

        It runs the function only if the value is present.

        It transforms the JournalEntry into something else — in this case, into a ResponseEntity.
        

            If journalEntry contains a value (i.e., entry was found),

    Then it runs the lambda: entry -> new ResponseEntity<>(entry, HttpStatus.OK),

    And returns ResponseEntity<JournalEntry> with 200 OK.

❌ If the Optional is empty?

    Then map(...) does not run.

    Instead, .orElseGet(...) is used.


         */
        return journalEntry
        .map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)) //if not null this runs
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); //if null this runs   



    }


    // @DeleteMapping("id/{myId}")
    // public boolean deleteById(@PathVariable ObjectId myId){
    //     journalEntryService.deleteById(myId);
    //     return true;
    // }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//     @PutMapping("id/{myId}")
//     public JournalEntry updateJournalbyId(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
//       //finding the journal with id
//       JournalEntry old = journalEntryService.getJournalById(myId).orElse(null);
//       if(old !=null){
//         old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle(): old.getTitle());
//         old.setContent(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getContent(): old.getContent());
//         /*
//          * Update the old object's fields only if the corresponding value in newEntry is not null and not empty .

// Otherwise, keep the old value.
//          */
//     }



//     journalEntryService.updateJournalEntry(old);
//       return old;

//     }

 @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalbyId(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
      //finding the journal with id
      JournalEntry old = journalEntryService.getJournalById(myId).orElse(null);
      if(old !=null){
        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle(): old.getTitle());
        old.setContent(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getContent(): old.getContent());
        journalEntryService.updateJournalEntry(old);
        return new ResponseEntity<>(old,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    

    }

}

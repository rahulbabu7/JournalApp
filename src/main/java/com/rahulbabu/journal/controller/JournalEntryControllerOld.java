package com.rahulbabu.journal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.rahulbabu.journal.entity.JournalEntry;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_journal")
public class JournalEntryControllerOld {
    
    private Map<String,JournalEntry> journalEntries = new HashMap<>();  //for a table view
    //in memory db
    /*
     * key = journalId long
     * value = JournalEntry object
     */


//Maps HTTP GET requests sent to /journal/all.

    @GetMapping()
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());

        /*
         *  getAll() method
         *  Returns a list of all journal entries by getting the values() from the Map.
         * Wraps them in an ArrayList so you return a List<JournalEntry>.
         
         * This is the method signature.
         * It returns a list of JournalEntry objects
         * These are likely your domain models/entities representing individual journal entries.
         * journalEntries.values()
         *     journalEntries is a Map<Long, JournalEntry>.
         * .values() gets all the values (i.e., the JournalEntry objects) from the map, not the keys (IDs).
         * 
         * 🟩 new ArrayList<>(...)
         * Converts the collection returned by .values() (which is a Collection<JournalEntry>) into an ArrayList.
         * This ensures that you’re returning a concrete List rather than a generic collection.

         */

         /*
          * 🧠 What Actually Happens

Let’s say this is your in-memory map:

journalEntries = {
    1L: new JournalEntry(1L, "Title 1", "Content 1"),
    2L: new JournalEntry(2L, "Title 2", "Content 2")
};

When you call /journal/all, the method:

    Grabs all values (JournalEntry objects) from the map.

    Wraps them in a list.

    Spring automatically serializes the list to JSON and returns it in the HTTP response.

🧾 Example JSON Response

[
  {
    "id": 1,
    "title": "Title 1",
    "content": "Content 1"
  },
  {
    "id": 2,
    "title": "Title 2",
    "content": "Content 2"
  }
]



   

*/


}


/*
 * It defines a POST endpoint (i.e., it listens for HTTP POST requests) that accepts a JSON payload, 
 * converts it into a JournalEntry Java object, and will (eventually) process/store it.
 * 
 * @RequestBody JournalEntry myentry
 * Tells Spring to automatically convert the JSON in the request body into a JournalEntry object.
 * This is done using Jackson, a JSON-to-Java mapper built into Spring Boot.
 * 
 * 
 * 🔽 Example JSON Request

{
  "id": 1,
  "title": "My First Entry",
  "content": "Today I started my journal!"
}

This will be converted into:

JournalEntry myentry = new JournalEntry(1L, "My First Entry", "Today I started my journal!");




 */

    @PostMapping()
    public boolean createEntry(@RequestBody JournalEntry myentry){

        //adding the data to the Map

        journalEntries.put(myentry.getId(), myentry);
        
        return true;

    }



    /*
     * 
     *  Breakdown of Key Parts
🔸 @GetMapping("id/{myId}")

    This maps HTTP GET requests to the URL:

    /journal/id/{someId}

    {myId} is a path variable — a dynamic part of the URL.

Example Request:

GET http://localhost:8080/journal/id/5




//!
@PathVariable Long myId

    Spring uses this annotation to extract the value from the URL and bind it to the myId method parameter.

    So if someone sends /journal/id/5, then myId = 5.



 //!
 return journalEntries.get(myId);

    journalEntries is a Map<Long, JournalEntry>.

    get(myId) fetches the journal entry with the given ID.

    If the ID exists, it returns the corresponding JournalEntry object.

    If the ID doesn't exist, it returns null.
    
    


     */
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable String myId){
        return journalEntries.get(myId);  //get(key) in a hashmap returns the corresponding value

    }


    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable String myId){
        journalEntries.remove(myId);

        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable String myId,@RequestBody JournalEntry myEntry){
        return journalEntries.put(myId,myEntry);

    }



}

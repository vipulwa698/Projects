package com.example.journalApp.Controllers;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Entity.Users;
import com.example.journalApp.Service.JournalEntryService;
import com.example.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalEntryControllerV2 { //created to connect with mongo db

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<JournalEntry> getAllEntry(){
        return journalEntryService.getAll();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName){
        Users user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping({"/{userName}"})
    public ResponseEntity<JournalEntry>  createEntry(@RequestBody JournalEntry entries, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(entries, userName);
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
         journalEntryService.deleteById(myId, userName);
         return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("userName/{userName}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String userName){
        journalEntryService.deleteJournalEntryById( userName);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry entry, @PathVariable String userName){
        JournalEntry old = journalEntryService.getById(myId).orElse(null);
        if(old!= null){
            old.setTitle(entry.getTitle()!= null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent()!= null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}

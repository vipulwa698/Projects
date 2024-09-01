package com.example.journalApp.Controllers;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalEntryControllerV2 { //created to connect with mongo db

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllEntry(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public  JournalEntry createEntry(@RequestBody JournalEntry entries){
        entries.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entries);
        return entries;
    }

    @GetMapping("id/{myId}")
    public Optional<JournalEntry> getEntryById(@PathVariable ObjectId myId){
        return Optional.ofNullable(journalEntryService.getById(myId).orElse(null));
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
         journalEntryService.deleteById(myId);
         return  true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry entry){
        JournalEntry old = journalEntryService.getById(myId).orElse(null);
        if(old!= null){
            old.setTitle(entry.getTitle()!= null && !entry.getTitle().equals("") ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent()!= null && !entry.getContent().equals("") ? entry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}

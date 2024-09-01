/*package com.example.journalApp.Controllers;

import com.example.journalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntry(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public  boolean createEntry(@RequestBody JournalEntry entries){
        journalEntries.put(entries.getId(),entries);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable String myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateEntry(@PathVariable long myId, @RequestBody JournalEntry entry){
        return journalEntries.put(myId, entry);
    }
}
*/
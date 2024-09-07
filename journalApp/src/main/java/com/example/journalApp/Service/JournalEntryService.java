package com.example.journalApp.Service;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Entity.Users;
import com.example.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        Users user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        Users user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id) );//deleting by ID for all user
        userService.saveEntry(user);
    }

    public void deleteJournalEntryById(String userName){
        Users user = userService.findByUserName(userName);
//        JournalEntry delete = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().clear();
        userService.saveEntry(user);
    }
}

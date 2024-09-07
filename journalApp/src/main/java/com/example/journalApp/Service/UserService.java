package com.example.journalApp.Service;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Entity.Users;
import com.example.journalApp.Repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    public void saveEntry(Users users){
        userEntryRepo.save(users);
    }

    public List<Users> getAll(){
        return userEntryRepo.findAll();
    }

    public Optional<Users> getById(ObjectId id){
        return userEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepo.deleteById(id);
    }

    public Users findByUserName(String username){
        return userEntryRepo.findByUserName(username);
    }
}

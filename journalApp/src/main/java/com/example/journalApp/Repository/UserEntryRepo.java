package com.example.journalApp.Repository;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<Users, ObjectId> {

    Users findByUserName(String username);
}

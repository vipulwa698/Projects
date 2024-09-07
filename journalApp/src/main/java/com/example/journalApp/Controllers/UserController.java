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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController { //created to connect with mongo db

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            userService.saveEntry(users);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody Users users, @PathVariable String username) {
        Users userFromDB = userService.findByUserName(username );
        if(userFromDB != null){
            userFromDB.setUserName(users.getUserName());
            userFromDB.setPassword(users.getPassword());
            userService.saveEntry(userFromDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

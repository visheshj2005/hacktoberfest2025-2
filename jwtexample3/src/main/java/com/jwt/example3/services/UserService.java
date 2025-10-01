package com.jwt.example3.services;

import com.jwt.example3.controllers.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store=new ArrayList<>();
    public UserService(){
        store.add(new User(UUID.randomUUID().toString(), "Saurabh", "saurabh123@.in" ));
        store.add(new User(UUID.randomUUID().toString(), "Rishubh", "rishub123@.in" ));
        store.add(new User(UUID.randomUUID().toString(), "Gaurav", "gaurav123@.in" ));
    }
    public List<User> getUsers(){
        return this.store;
    }
}

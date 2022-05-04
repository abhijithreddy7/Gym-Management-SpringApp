package com.abhijith.gymapp.service;

import com.abhijith.gymapp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();

    public User findByUsername(String username);

    public void save(User theUser);

    public void deleteById(String username);
}

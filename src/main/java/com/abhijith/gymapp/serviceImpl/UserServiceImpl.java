package com.abhijith.gymapp.serviceImpl;

import com.abhijith.gymapp.entity.User;
import com.abhijith.gymapp.exception.RecordNotFoundException;
import com.abhijith.gymapp.repository.UserRepository;
import com.abhijith.gymapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository=theUserRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        User theUser = userRepository.findByUsername(username);

        if (theUser==null) {
            throw new RecordNotFoundException("Did not find User with username - " + username);
        }
        return theUser;
    }

    @Override
    public void save(User theUser) {
        String encodedPassword = this.passwordEncoder.encode(theUser.getPassword());
        theUser.setPassword(encodedPassword);
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(String username) {
        userRepository.deleteById(username);
    }

}

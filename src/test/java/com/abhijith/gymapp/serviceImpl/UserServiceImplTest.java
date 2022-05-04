package com.abhijith.gymapp.serviceImpl;

import com.abhijith.gymapp.entity.User;
import com.abhijith.gymapp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void findAll() {
        List<User> users = new ArrayList<>();

        User user1 = new User("ram","hello123","TRAINER");
        User user2 = new User("laxman","hello123","TRAINER");

        users.add(user1);
        users.add(user2);

        when(repository.findAll()).thenReturn(users);

        List<User> userList = service.findAll();

        Java6Assertions.assertThat(2).isEqualTo(userList.size());
        verify(repository,times(1)).findAll();
    }

    @Test
    void findByUsername() {

        when(repository.findByUsername("bharat")).thenReturn(new User("bharat","hello123","TRAINER"));

        User user = service.findByUsername("bharat");

        Assertions.assertThat(user.getRole()).isEqualTo("TRAINER");
        verify(repository,times(1)).findByUsername("bharat");
    }

    @Test
    void save() {
        User user = new User("shatru","hello123","TRAINER");
        service.save(user);
        verify(repository).save(user);
    }

    @Test
    void deleteById(){
        service.deleteById("shatru");
        verify(repository).deleteById("shatru");
    }
}
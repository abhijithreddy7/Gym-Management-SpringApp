package com.abhijith.gymapp.serviceImpl;

import com.abhijith.gymapp.entity.Client;
import com.abhijith.gymapp.repository.ClientRepository;
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

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientServiceImplTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImpl service;

    @Test
    void findAll() {
        service = new ClientServiceImpl(repository);

        List<Client> clientList = new ArrayList<>();

        Client client1 = new Client("Abhijith","Mandagiri","abhijith@gmail.com","9701199599");
        Client client2 = new Client("Bhavana","Mandagiri","Bhavanam@gmail.com","9494161416");

        clientList.add(client1);
        clientList.add(client2);


        when(repository.findAllByOrderByLastNameAsc()).thenReturn(clientList);

        List<Client> expectedList = service.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findById() {

        when(repository.findById(2)).thenReturn(Optional.of(new Client("Rachel", "Green", "rachel@gmail.com", "9666418866")));

        Client client = service.findById(2);

        Assertions.assertThat(client.getFirstName()).isEqualTo("Rachel");
        Assertions.assertThat(client.getLastName()).isEqualTo("Green");
        Assertions.assertThat(client.getEmail()).isEqualTo("rachel@gmail.com");
        Assertions.assertThat(client.getContact()).isEqualTo("9666418866");
        verify(repository,times(1)).findById(2);


    }

    @Test
    void save() {
        Client client = new Client("Abhi","Venna","abhivenna@gmail.com","9876543210");
        service.save(client);
        verify(repository).save(client);
    }

    @Test
    void deleteById(){
        service.deleteById(3);
        verify(repository).deleteById(3);
    }
}
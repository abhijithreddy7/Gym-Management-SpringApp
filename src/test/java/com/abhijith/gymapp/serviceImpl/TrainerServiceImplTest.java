package com.abhijith.gymapp.serviceImpl;

import com.abhijith.gymapp.entity.Client;
import com.abhijith.gymapp.entity.Trainer;
import com.abhijith.gymapp.repository.TrainerRepository;
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
class TrainerServiceImplTest {

    @Mock
    private TrainerRepository repository;

    @InjectMocks
    private TrainerServiceImpl service;

    @Test
    void findAll() {
        service = new TrainerServiceImpl(repository);

        List<Trainer> trainers = new ArrayList<>();

        Trainer trainer1 = new Trainer("Abhijith","Mandagiri","AbhiMandagiri","9701199599","abhijith@gmail.com","4");
        Trainer trainer2 = new Trainer("Bhavana","Mandagiri","BhavanaMandagiri","9494161416","Bhavanam@gmail.com","3");

        trainers.add(trainer1);
        trainers.add(trainer2);


        when(repository.findAllByOrderByLastNameAsc()).thenReturn(trainers);

        List<Trainer> expectedList = service.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findById() {

        when(repository.findById(2)).thenReturn(Optional.of(new Trainer("Anirudh","Revalli","AnirudhRevalli","9666428866","anirudh@gmail.com","3")));

        Trainer trainer = service.findById(2);

        Assertions.assertThat(trainer.getFirstName()).isEqualTo("Anirudh");
        Assertions.assertThat(trainer.getLastName()).isEqualTo("Revalli");
        Assertions.assertThat(trainer.getTrainerName()).isEqualTo("AnirudhRevalli");
        Assertions.assertThat(trainer.getContact()).isEqualTo("9666428866");
        Assertions.assertThat(trainer.getEmail()).isEqualTo("anirudh@gmail.com");
        Assertions.assertThat(trainer.getExperience()).isEqualTo("3");
        verify(repository,times(1)).findById(2);


    }

    @Test
    void save() {
        Trainer trainer = new Trainer("Nikhil","Valaja","NikhilValaja","7654321980","nikhilvalaja@gmail.com","5");
        service.save(trainer);
        verify(repository).save(trainer);
    }

    @Test
    void deleteById(){
        service.deleteById(2);
        verify(repository).deleteById(2);
    }
}
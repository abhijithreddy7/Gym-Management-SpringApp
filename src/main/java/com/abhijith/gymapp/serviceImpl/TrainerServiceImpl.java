package com.abhijith.gymapp.serviceImpl;

import com.abhijith.gymapp.entity.Trainer;
import com.abhijith.gymapp.repository.TrainerRepository;
import com.abhijith.gymapp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

	private TrainerRepository trainerRepository;

	@Autowired
	public TrainerServiceImpl(TrainerRepository theTrainerRepository) {
		trainerRepository = theTrainerRepository;
	}
	
	@Override
	public List<Trainer> findAll() {
		return trainerRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Trainer findById(int theId) {
		Optional<Trainer> result = trainerRepository.findById(theId);
		
		Trainer theTrainer = null;
		
		if (result.isPresent()) {
			theTrainer = result.get();
		}
		else {
			throw new RuntimeException("Did not find Trainer id - " + theId);
		}
		
		return theTrainer;
	}

	@Override
	public void save(Trainer theTrainer) {
		trainerRepository.save(theTrainer);
	}

	@Override
	public void deleteById(int theId) {
		trainerRepository.deleteById(theId);
	}

}







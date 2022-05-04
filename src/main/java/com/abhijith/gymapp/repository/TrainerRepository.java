package com.abhijith.gymapp.repository;

import com.abhijith.gymapp.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	
	// add a method to sort by last name
	public List<Trainer> findAllByOrderByLastNameAsc();
	
}

package com.abhijith.gymapp.service;

import com.abhijith.gymapp.entity.Trainer;

import java.util.List;

public interface TrainerService {

	public List<Trainer> findAll();
	
	public Trainer findById(int theId);
	
	public void save(Trainer theTrainer);
	
	public void deleteById(int theId);
	
}

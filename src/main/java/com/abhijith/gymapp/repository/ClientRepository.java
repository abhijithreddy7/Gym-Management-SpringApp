package com.abhijith.gymapp.repository;


import com.abhijith.gymapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	
	// add a method to sort by last name
	public List<Client> findAllByOrderByLastNameAsc();
	
}

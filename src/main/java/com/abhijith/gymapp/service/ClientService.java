package com.abhijith.gymapp.service;


import com.abhijith.gymapp.entity.Client;

import java.util.List;

public interface ClientService {

	public List<Client> findAll();
	
	public Client findById(int theId);
	
	public void save(Client theClient);
	
	public void deleteById(int theId);
	
}

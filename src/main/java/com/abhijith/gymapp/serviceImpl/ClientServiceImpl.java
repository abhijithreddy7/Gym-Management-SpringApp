package com.abhijith.gymapp.serviceImpl;

import com.abhijith.gymapp.entity.Client;
import com.abhijith.gymapp.exception.RecordNotFoundException;
import com.abhijith.gymapp.repository.ClientRepository;
import com.abhijith.gymapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;
	
	@Autowired
	public ClientServiceImpl(ClientRepository theClientRepository) {
		clientRepository = theClientRepository;
	}
	
	@Override
	public List<Client> findAll() {
		return clientRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Client findById(int theId) {
		Optional<Client> result = clientRepository.findById(theId);
		
		Client theClient = null;
		
		if (result.isPresent()) {
			theClient = result.get();
		}
		else {
			throw new RecordNotFoundException("Did not find Client id - " + theId);
		}
		
		return theClient;
	}

	@Override
	public void save(Client theClient) {
		clientRepository.save(theClient);
	}

	@Override
	public void deleteById(int theId) {
		clientRepository.deleteById(theId);
	}

}







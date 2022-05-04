package com.abhijith.gymapp.controller;

import com.abhijith.gymapp.entity.Client;
import com.abhijith.gymapp.entity.Trainer;
import com.abhijith.gymapp.service.ClientService;
import com.abhijith.gymapp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private TrainerService trainerService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}

	@GetMapping("/list")
	public String listClients(Model theModel) {

		List<Client> theClients = clientService.findAll();

		theModel.addAttribute("clients", theClients);
		
		return "clients/list-clients";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		List<Trainer> trainers = trainerService.findAll();

		theModel.addAttribute("trainers",trainers);

		Client theClient = new Client();
		
		theModel.addAttribute("client", theClient);
		
		return "clients/client-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("clientId") int theId, Model theModel) {

		List<Trainer> trainers = trainerService.findAll();

		theModel.addAttribute("trainers",trainers);

		Client theClient = clientService.findById(theId);

		theModel.addAttribute("client", theClient);

		return "clients/client-form";
	}
	
	
	@PostMapping("/save")
	public String saveClient(@Valid @ModelAttribute("client") Client theClient, BindingResult theBindingResult,Model theModel) {

		if(theBindingResult.hasErrors()){

			List<Trainer> trainers = trainerService.findAll();

			theModel.addAttribute("trainers",trainers);

			System.out.println("binding result:"+theBindingResult);

			return "clients/client-form";
		}
		else {
			clientService.save(theClient);

			return "redirect:/clients/list";
		}
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("clientId") int theId) {

		clientService.deleteById(theId);

		return "redirect:/clients/list";
		
	}
}



















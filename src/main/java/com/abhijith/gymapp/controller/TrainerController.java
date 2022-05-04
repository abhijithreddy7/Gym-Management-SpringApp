package com.abhijith.gymapp.controller;

import com.abhijith.gymapp.entity.Trainer;
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
@RequestMapping("/trainers")
public class TrainerController {
	@Autowired
	private TrainerService trainerService;

	public TrainerController(TrainerService theTrainerService) {
		trainerService = theTrainerService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}

	@GetMapping("/list")
	public String listTrainers(Model theModel) {

		List<Trainer> theTrainers = trainerService.findAll();

		theModel.addAttribute("trainers", theTrainers);
		
		return "trainers/list-trainers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Trainer theTrainer = new Trainer();
		
		theModel.addAttribute("trainer", theTrainer);
		
		return "trainers/trainer-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("trainerId") int theId, Model theModel) {

		Trainer theTrainer = trainerService.findById(theId);

		theModel.addAttribute("trainer", theTrainer);

		return "trainers/trainer-form";
	}
	
	
	@PostMapping("/save")
	public String saveTrainer(@Valid @ModelAttribute("trainer") Trainer theTrainer, BindingResult theBindingResult,Model theModel) {

		try{
			if(theBindingResult.hasErrors()){

				List<Trainer> trainers = trainerService.findAll();

				theModel.addAttribute("trainers",trainers);

				System.out.println("binding result:"+theBindingResult);

				return "trainers/trainer-form";
			}
			else {
				trainerService.save(theTrainer);

				return "redirect:/trainers/list";
			}
		}
		catch(Exception ex)
		{
			return "security/error";
		}

	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("trainerId") int theId) {

		trainerService.deleteById(theId);

		return "redirect:/trainers/list";
		
	}
}



















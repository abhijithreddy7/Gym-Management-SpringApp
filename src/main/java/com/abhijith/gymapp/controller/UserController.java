package com.abhijith.gymapp.controller;

import com.abhijith.gymapp.entity.Trainer;
import com.abhijith.gymapp.entity.User;
import com.abhijith.gymapp.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService theUserService) {
		userService = theUserService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}

	@GetMapping("/list")
	public String listUsers(Model theModel) {

		List<User> theUsers = userService.findAll();

		theModel.addAttribute("users", theUsers);
		
		return "users/list-users";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		User theUser = new User();
		
		theModel.addAttribute("user", theUser);
		
		return "users/user-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("username") String username, Model theModel) {

		User theUser = userService.findByUsername(username);

		theModel.addAttribute("user", theUser);

		return "users/user-form";
	}
	
	
	@PostMapping("/save")
	public String saveTrainer(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult, Model theModel) {

		try{
			if(theBindingResult.hasErrors()){

				List<User> users = userService.findAll();

				theModel.addAttribute("users",users);

				System.out.println("binding result:"+theBindingResult);

				return "users/user-form";
			}
			else {

				userService.save(theUser);

				return "redirect:/users/list";
			}
		}
		catch (Exception ex)
		{
			return "security/error";
		}

	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("username") String username) {

		userService.deleteById(username);

		return "redirect:/users/list";
		
	}
}



















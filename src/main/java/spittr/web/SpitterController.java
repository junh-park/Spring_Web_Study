package spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	private SpitterRepository spitterRepo;
	
	@Autowired
	public SpitterController(SpitterRepository spitterRepo) {
		this.spitterRepo = spitterRepo;
	}

	public SpitterController() {
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "registerForm";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors) {
		if(errors.hasErrors()) return "registerForm";
		
		spitterRepo.save(spitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spitter spitter = spitterRepo.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}

package spittr.domain.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.domain.Spitter;
import spittr.domain.data.SpitterRepository;

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
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, RedirectAttributes model, Errors errors) {
		if(errors.hasErrors()) return "registerForm";
		
		spitterRepo.save(spitter);
		model.addAttribute("username", spitter.getUsername());
		model.addFlashAttribute("spitter", spitter);
		return "redirect:/spitter/{username}";
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if(!model.containsAttribute("spitter")) model.addAttribute(spitterRepo.findByUsername(username));
		Spitter spitter = spitterRepo.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}

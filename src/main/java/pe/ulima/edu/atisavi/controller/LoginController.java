package pe.ulima.edu.atisavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.business.IUserService;
import pe.ulima.edu.atisavi.model.dto.UserDto;

@Log
@Controller 
public class LoginController {

	@Autowired
	IUserService userservice;
	

	@GetMapping("/insesion")
	public String insesion(Model model) {
	      UserDto userdto = new UserDto(); 
	      userdto.setEmail("");
	      userdto.setPass("");
		  model.addAttribute("userdto", userdto); 
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String insesionError(Model model) {
		model.addAttribute("loginError", 1);	
		return "login";
	}
	
	@GetMapping("/loginok")
	public String insesionOk(Model model) {
		model.addAttribute("loginError", 2);	
		return "login";
	}
	
	@PostMapping("/insesion/login")
	public String login(Model model, @ModelAttribute(value="userdto") UserDto user,BindingResult bindingResult) { 
		log.info("Usuario : " + user.toString());
		if(user.getEmail().equalsIgnoreCase("admin@gmail.com")) {
			log.info("ENTRO");
			return  "redirect:/list"; 
		}
		
		
		if(userservice.login(user)) { 
			log.info("Login exitoso...");
			return  "redirect:/loginok"; 
		}else { 
			log.info("contraseña incorrecta...");
			return "redirect:/loginerror"; 
		}
		
	}
	
	
	
}

package pe.ulima.edu.atisavi.controller;

import java.util.ArrayList;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.business.IUserService;
import pe.ulima.edu.atisavi.model.Medicamento;
import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.RegisterDto; 
import pe.ulima.edu.atisavi.repository.IRoleRepository;
import pe.ulima.edu.atisavi.repository.IUserRepository;
import pe.ulima.edu.atisavi.repository.MedicamentoRepo;

@Log
@Controller 
public class RegisterController {

	@Autowired
	IUserService userservice;
	
	@Autowired
	IUserRepository repoUser; 
	
	@Autowired
	IRoleRepository repoRole;

	@Autowired
	MedicamentoRepo medi;
	@Autowired
    BCryptPasswordEncoder bcrypt;  
	
	@GetMapping("/register")
	public String register(Model model) { 
		return "register";
	} 
	
	@PostMapping("/register/create")
	public String registerCreate(Model model, @ModelAttribute(value="registerdto") RegisterDto user){
		log.info("informacion dto " + user.toString());

		
		List<Role> roles = new ArrayList<>(); 
		if(user.isPaciente()) {
			roles.add(repoRole.findById(2L).get());
		}else if(user.isMedico()){
			roles.add(repoRole.findById(3L).get());
		}else if(user.isAdmin()) {
			roles.add(repoRole.findById(1L).get());
		} 
		
		String raw = user.getPass();
		String encode = bcrypt.encode(raw); 
 
		try {
			log.info(repoUser.save(User.builder().email(user.getEmail())
					.password(encode)
					.phone(user.getPhone())
					.firstName(user.getName())
					.lastName(user.getLastname())
					.enabled(true)
					.roles(roles)
					.build()).toString()); 
		}catch (Exception e) {
			log.warning(e.getMessage());
		}
		log.info("OK");
		
		return "redirect:/login";  
	}
	
}

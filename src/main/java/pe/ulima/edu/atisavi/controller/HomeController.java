package pe.ulima.edu.atisavi.controller;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;  
import lombok.extern.java.Log; 
import pe.ulima.edu.atisavi.repository.IRoleRepository;
import pe.ulima.edu.atisavi.repository.IUserRepository;


@Log
@Controller
public class HomeController {

	@Autowired
	IUserRepository repoUser; 
	@Autowired
	IRoleRepository repoRole;
	
	@Autowired
    BCryptPasswordEncoder bcrypt;  


	@GetMapping("/")
	public String basic() {  
		return "redirect:/home"; 
	}	
	 
	@GetMapping("/home")
	public String basichome() { 
		log.info("ENTRO");
		
		
		return "index"; 
	}	
}

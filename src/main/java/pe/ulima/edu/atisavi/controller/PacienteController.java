package pe.ulima.edu.atisavi.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository;

@Controller 
@Log
public class PacienteController {

	
	@Autowired
    private IUserRepository repository; 
	
		/*Pantalla principal de paciente*/
	    @GetMapping(path = {"/paciente", "/paciente/"})
	    public String doctor( Model model, Principal principal){   
	    	final String loggedInUserName = principal.getName();
	    	 model.addAttribute("doctor", repository.findByEmail(loggedInUserName));
	    	return "paciente";
	    }
	
		/*Historia 14 */
		@GetMapping("/paciente/info")
		public String getAll(Model model, Principal principal){ 
				model.addAttribute("paciente", repository.findByEmail(principal.getName())); 
		        return "paciente_info";
		}
 
	     
	    
	    /*editar paciente info */
	    @PostMapping("/paciente/addEdit")
	    public String pacienteUpdate(UserDto usuario1){  
	    		log.info(usuario1.toString());
	            Optional<User> usuario1Optional = repository.findByEmail(usuario1.getMail()); 
	            if(usuario1Optional.isPresent()){   
	            	User editUser = usuario1Optional.get();
	            	editUser.setId(usuario1.getIdentifier());
	                editUser.setEmail(usuario1.getMail());
	                editUser.setFirstName(usuario1.getFirstName());
	                editUser.setLastName(usuario1.getLastName());
	                editUser.setPhone(usuario1.getPhone());  
	                repository.save(editUser); 
	            } 
	        return "redirect:/paciente";
	    }

	  

}

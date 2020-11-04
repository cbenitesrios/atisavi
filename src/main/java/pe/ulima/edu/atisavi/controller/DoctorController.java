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
public class DoctorController {

	
	@Autowired
    private IUserRepository repository; 
	
		/*Pantalla principal de doctor*/
	    @GetMapping(path = {"/doctor"})
	    public String doctor( Model model, Principal principal){   
	    	final String loggedInUserName = principal.getName();
	    	 model.addAttribute("doctor", repository.findByEmail(loggedInUserName));
	    	return "doctor";
	    }
	
		/*Historia 16 - 17 */
		@GetMapping("/doctor/listarpacientes/{id}")
		public String getAll(Model model, @PathVariable("id") Optional<Long> id){
			 if(id.isPresent()){
				 model.addAttribute("pacientes", repository.findById(id.get()).get());
			 }else {
				 model.addAttribute("pacientes", repository.findByRolesIn(Arrays.asList("PACIENTE")));
			 } 
		        return "pacientes_list";
		}


	    /*Pantalla editar doctor*/
	    @GetMapping(path = {"/doctor/editar"})
	    public String doctoredit( Model model, Principal principal){   
	    	final String loggedInUserName = principal.getName();
	    	 model.addAttribute("doctor", repository.findByEmail(loggedInUserName));
	    	return "doctor_editar";
	    }
	     
	    
	    /*editar doctor */
	    @PostMapping("/doctor/addEdit")
	    public String insertOrUpdate(UserDto usuario1){  
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
	        
	        return "redirect:/doctor";
	    }

	  

}

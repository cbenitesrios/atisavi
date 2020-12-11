package pe.ulima.edu.atisavi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import pe.ulima.edu.atisavi.model.dto.RecetaList;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository;
import pe.ulima.edu.atisavi.repository.RecetaRepository;

@Controller 
@Log
public class PacienteController {

	
	@Autowired
    private IUserRepository repository; 

	@Autowired
    private RecetaRepository repository2; 
	   	
	 	
	    @GetMapping("/pacientefecha")
	   	public String Pac3() {   
	   		return "VistaPacFec"; 
	   	}	
	    @GetMapping("/pacienteinfo")
	   	public String Pac4() {   
	   		return "VistaPacInf"; 
	   	}

	
	
		/*Pantalla principal de paciente*/
	    @GetMapping(path = {"/paciente", "/paciente/"})
	    public String doctor( Model model, Principal principal){   
	    	final String loggedInUserName = principal.getName();
	    	 model.addAttribute("pacientes", repository.findByEmail(loggedInUserName));
	    	 List<RecetaList> receta= new ArrayList<>();
	    	 repository2.findAll().stream().filter(a-> a.getNamePac()
	    			 .equalsIgnoreCase(repository.findByEmail(loggedInUserName).get().getFirstName()))
	     	.forEach(a-> receta.add(
	     			RecetaList.builder()
	     			.cantidad(a.getMedicamentoSoli().get(0).getQuantity())
	     			.creado(a.getCreateDate())
	     			.doctor(a.getNameDoc()) 
	     			.medicamento(a.getMedicamentoSoli().get(0).getMedicine().getName())
	     			.pickdate(a.getMedicamentoSoli().get(0).getPickDate())
	     			.build()
	     			));
	         model.addAttribute("recetas", receta );
	    	return "VistaPaciente";
	    }
	
		/*Historia 14 */
		@GetMapping("/paciente/info")
		public String getAll(Model model, Principal principal){ 
				model.addAttribute("pacientes", repository.findByEmail(principal.getName())); 
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

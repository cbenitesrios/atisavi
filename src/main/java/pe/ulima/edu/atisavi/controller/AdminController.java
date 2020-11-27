package pe.ulima.edu.atisavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller 
@Log
public class AdminController {
	@Autowired
    private IUserRepository repository; 
	

	
    @GetMapping("/admin/list")
    public String getAll(Model model, Principal principal){
    	log.info("USUARIO LOGEADO: " +  principal.getName()); 
        model.addAttribute("administradores", repository.findAll());
        return "VistaAdmin";
    }
    @GetMapping("/adminreceta")
	public String PagAdmin1() {   
		return "VistaAdminRec"; 
	}
    @GetMapping("/adminstock")
   	public String PagAdmin2() {   
   		return "VistaAdminStck"; 
   	}	
 	
    @GetMapping("/adminmedico")
   	public String PagAdmin4() {   
   		return "VistaAdminMed"; 
   	}	
    @GetMapping("/adminpaciente")
   	public String PagAdmin() {   
   		return "VistaAsminPac"; 
   	}

    @GetMapping(path = {"/admin/add", "/admin/edit/{id}"})
    public String addForm(@PathVariable("id") Optional<Long> id, Model model){  
        if(id.isPresent()){
        	model.addAttribute("addUser", false);
        	User user = repository.findById(id.get()).get();
            model.addAttribute("administradores",
            		UserDto.builder()
            		.identifier(user.getId())
            		.mail(user.getEmail())
            		.pass("")
            		.firstName(user.getFirstName())
            		.lastName(user.getLastName())
            		.phone(user.getPhone())
            		.enabled(true)
            		.build());  
            		
        }else{
        	model.addAttribute("addUser", true);
            model.addAttribute("administradores", new UserDto());
        }
        return "add_edit_user";
    }

    @PostMapping("/admin/addEdit")
    public String insertOrUpdate(UserDto usuario1){  
    	log.info(usuario1.toString());
            Optional<User> usuario1Optional = repository.findByEmail(usuario1.getMail());
 
            if(usuario1Optional.isPresent()){  
            	log.info(usuario1Optional.get().toString());
            	User editUser = usuario1Optional.get();
            	editUser.setId(usuario1.getIdentifier());
                editUser.setEmail(usuario1.getMail());
                editUser.setFirstName(usuario1.getFirstName());
                editUser.setLastName(usuario1.getLastName());
                editUser.setPhone(usuario1.getPhone()); 
                
                log.info(usuario1Optional.get().toString());
                log.info(editUser.toString());
                repository.save(editUser);
            }else {
            	log.info("ENTRO A SAVEAR");
            	repository.save(User.builder()
            			.email(usuario1.getMail())
            			.password(usuario1.getPass())
            			.firstName(usuario1.getFirstName())
            			.lastName(usuario1.getLastName())
            			.phone(usuario1.getPhone())
            			.enabled(true)
            			.build());
            }
        
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        Optional<User> user1 = repository.findById(id);
        if(user1.isPresent()){
            repository.delete(user1.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/admin/list";
    }

}

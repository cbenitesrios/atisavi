package pe.ulima.edu.atisavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository; 

import java.util.Optional;

@Controller 
@Log
public class UserController {
	@Autowired
    private IUserRepository repository; 
	

	
    @GetMapping("/admin/list")
    public String getAll(Model model){
        model.addAttribute("usuario", repository.findAll());
        return "user_list";
    }

    @GetMapping(path = {"/admin/add", "/admin/edit/{id}"})
    public String addForm(@PathVariable("id") Optional<Long> id, Model model){  
        if(id.isPresent()){
        	model.addAttribute("addUser", false);
        	User user = repository.findById(id.get()).get();
            model.addAttribute("usuarios",
            		UserDto.builder()
            		.id(user.getId())
            		.email(user.getEmail())
            		.password(user.getPassword())
            		.firstName(user.getFirstName())
            		.lastName(user.getLastName())
            		.phone(user.getPhone())
            		.enabled(true)
            		.build());  
            		
        }else{
        	model.addAttribute("addUser", true);
            model.addAttribute("usuarios", new UserDto());
        }
        return "add_edit_user";
    }

    @PostMapping("/admin/addEdit")
    public String insertOrUpdate(UserDto usuario1){ 
    	log.info(usuario1.toString());
            Optional<User> usuario1Optional = repository.findByEmail(usuario1.getEmail());
            if(usuario1Optional.isPresent()){ 
            	log.info("ENTRO AL EDITAR");
            	log.info(usuario1Optional.get().toString());
            	User editUser = usuario1Optional.get();
            	editUser.setId(usuario1.getId());
                editUser.setEmail(usuario1.getEmail());
                editUser.setFirstName(usuario1.getFirstName());
                editUser.setLastName(usuario1.getLastName());
                editUser.setPhone(usuario1.getPhone()); 
                repository.save(editUser);
            }else {
            	repository.save(User.builder()
            			.email(usuario1.getEmail())
            			.password(usuario1.getPassword())
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

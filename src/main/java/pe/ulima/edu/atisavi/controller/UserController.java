package pe.ulima.edu.atisavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.repository.IUserRepository; 

import java.util.Optional;

@Controller
@Log
public class UserController {
	@Autowired
    private IUserRepository repository; 
	

    @GetMapping("/list")
    private String getAll(Model model){
        model.addAttribute("usuario", repository.findAll());
        return "user_list";
    }

    @GetMapping(path = {"/add", "edit/{id}"})
    private String addForm(@PathVariable("id") Optional<Long> id, Model model){
    	
    	log.info(id.toString());
        if(id.isPresent()){
            model.addAttribute("usuarios", repository.findById(id.get()));
        }else{
            model.addAttribute("usuarios", new User());
        }
        return "add_edit_user";
    }

    @PostMapping("/addEdit")
    private String insertOrUpdate(User usuario1){
        if(usuario1.getId() == null){
        	repository.save(usuario1);
        }else{
            Optional<User> usuario1Optional = repository.findById(usuario1.getId());
            if(usuario1Optional.isPresent()){
            	User editUser = usuario1Optional.get();
                editUser.setEmail(usuario1.getEmail());
                editUser.setLastname(usuario1.getLastname());
                editUser.setPhone(usuario1.getPhone()); 
                repository.save(editUser);
            }
        }
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    private String deleteUser(@PathVariable("id") Long id){
        Optional<User> user1 = repository.findById(id);
        if(user1.isPresent()){
            repository.delete(user1.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/list";
    }

}

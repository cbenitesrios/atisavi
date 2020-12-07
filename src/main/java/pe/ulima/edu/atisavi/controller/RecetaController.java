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
import pe.ulima.edu.atisavi.business.IUserService;
import pe.ulima.edu.atisavi.model.Receta;
import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.RecetaDto;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository;
import pe.ulima.edu.atisavi.repository.RecetaRepository;

@Controller 
@Log

public class RecetaController {
	@GetMapping("/pacientereceta")
	public String Rec() {   
		return "VistaPacRec"; 
	}
	@GetMapping("/pacientereceta/crear")
	public String Rec1() {   
		return "crearReceta"; 
		
	}
	@Autowired
    private RecetaRepository repository; 
	@Autowired
	IUserService userservice;
	
	@GetMapping("/receta/list")
    public String getAll(Model model, Principal principal){
    	log.info("USUARIO LOGEADO: " +  principal.getName()); 
        model.addAttribute("recetas", repository.findAll());
        return "VistaMedRec";
}
	
    @GetMapping(path = {"/receta/add", "/receta/edit/{id}"})
    public String addForm(@PathVariable("id") Optional<Long> id, Model model){  
        if(id.isPresent()){
        	model.addAttribute("addReceta", false);
        	Receta receta = repository.findById(id.get()).get();
            model.addAttribute("recetas",
            		RecetaDto.builder()
            		.identifier(receta.getId())
            		.medicamento(receta.getMedicamento())
            		.cantidad(receta.getCantidad())
            		.build());  
            		
        }else{
        	model.addAttribute("addReceta", true);
            model.addAttribute("recetas", new RecetaDto());
        }
        return "add_edit_receta";
    }

    @PostMapping("/receta/addEdit")
    public String insertOrUpdate(Model model, RecetaDto receta1){  
    	
    	log.info(receta1.toString());
            Optional<Receta> receta1Optional = repository.findById(receta1.getIdentifier());
           // model.addAttribute("administrador", repository.findByEmail(receta1.getMail()));
            if(receta1Optional.isPresent()){  
            	log.info(receta1Optional.get().toString());
            	Receta editReceta = receta1Optional.get();
            	editReceta.setId(receta1.getIdentifier());
                editReceta.setMedicamento(receta1.getMedicamento());
                editReceta.setCantidad(receta1.getCantidad());
                
                log.info(receta1Optional.get().toString());
                log.info(editReceta.toString());
                repository.save(editReceta);
            }else {
            	log.info("ENTRO A SAVEAR");
            	repository.save(Receta.builder()
            			.id(receta1.getIdentifier())
            			.medicamento(receta1.getMedicamento())
            			.cantidad(receta1.getCantidad())
            			.build());
            	
            }
        
        return "redirect:/receta/list";
    }

    @GetMapping("/receta/delete/{id}")
    public String deleteReceta(@PathVariable("id") Long id){
        Optional<Receta> receta1 = repository.findById(id);
        if(receta1.isPresent()){
            repository.delete(receta1.get());
        }else{
            System.err.println("not found");
        }
        return "redirect:/receta/list";
    }
	
	
	
}
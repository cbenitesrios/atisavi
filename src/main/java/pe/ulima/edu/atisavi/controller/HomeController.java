package pe.ulima.edu.atisavi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.repository.IRoleRepository;
import pe.ulima.edu.atisavi.repository.IUserRepository;


@Log
@Controller
public class HomeController {

	@Autowired
	IUserRepository repoUser; 
	@Autowired
	IRoleRepository repoRole;
	
	@GetMapping("/")
	public String basic() { 
		log.info("ENTRO");
		repoRole.save(Role.builder().id(1l).rolename("ADMIN").build());
		repoRole.save(Role.builder().id(2l).rolename("USUARIO").build());
		repoRole.save(Role.builder().id(3l).rolename("DOCTOR").build());
		List<Role> roles = new ArrayList<>();
		roles.add(repoRole.findById(1L).get());
		roles.add(repoRole.findById(2L).get());
		roles.add(repoRole.findById(3L).get());
		
		repoUser.save(User.builder().email("caran960606@gmail.com")
								.password("123")
								.fullname("carlos benites rios")
								.enabled(true)
								.role(roles)
								.build());
		
		return "redirect:/home";
	}	
	
	@GetMapping("/home")
	public String home(@RequestParam(name="name", required=false, defaultValue="hola") String name, Model model) {
		model.addAttribute("name", name);
		return "PantallaPrincipal";
	}

	@GetMapping("/qs")
	public String qs( Model model) {
		model.addAttribute("name");
		return "QS";
	}
	@GetMapping("/ayuda")
	public String ayuda( Model model) {
		model.addAttribute("name");
		return "Ayuda";
	}
	@GetMapping("/insesion")
	public String insesion(Model model) {
		model.addAttribute("name");
		return "InSesion";
	}
	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("name");
		return "Registro";
	}
}

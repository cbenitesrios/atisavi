package pe.ulima.edu.atisavi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

	
	@GetMapping("/")
	public String basic() {
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

package pe.ulima.edu.atisavi.controller;
 
 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;  
import lombok.extern.java.Log;  


@Log
@Controller
public class HomeController {
 

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

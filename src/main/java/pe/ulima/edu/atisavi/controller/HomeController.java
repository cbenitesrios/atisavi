package pe.ulima.edu.atisavi.controller;  
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;  
import lombok.extern.java.Log;  


@Log
@Controller
public class HomeController {
 
	 
	@GetMapping(path = {"/", "/home"})
	public String home() {   
		return "index"; 
	}	
}

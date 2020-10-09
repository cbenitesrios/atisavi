package pe.ulima.edu.atisavi;
  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;  
 

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AtisaviApplication {
 
	
	public static void main(String[] args) {
		SpringApplication.run(AtisaviApplication.class, args); 
		
	}

 
}

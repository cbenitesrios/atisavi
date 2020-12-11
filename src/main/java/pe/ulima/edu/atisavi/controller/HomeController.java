package pe.ulima.edu.atisavi.controller;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;  
import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.model.Medicamento;
import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.repository.IRoleRepository;
import pe.ulima.edu.atisavi.repository.MedicamentoRepo;  


@Log
@Controller
public class HomeController {
 
	@Autowired
	IRoleRepository repoRole;

	@Autowired
	MedicamentoRepo medi;
	@GetMapping(path = {"/", "/home"})
	public String home() {
		repoRole.save(Role.builder().id(1l).name("ADMIN").build());
		repoRole.save(Role.builder().id(2l).name("PACIENTE").build());
		repoRole.save(Role.builder().id(3l).name("DOCTOR").build());
		medi.save(Medicamento.builder()
					.name("medicamento1")
					.stock(20)
					.build());
		medi.save(Medicamento.builder()
					.name("medicamento2")
					.stock(20)
					.build());
			 
		return "index"; 
	}	
}

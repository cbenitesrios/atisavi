package pe.ulima.edu.atisavi.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.business.IUserService;
import pe.ulima.edu.atisavi.model.Medicamento;
import pe.ulima.edu.atisavi.model.MedicamentoSolicitud;
import pe.ulima.edu.atisavi.model.Receta;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.RecetaDto;
import pe.ulima.edu.atisavi.model.dto.RecetaList;
import pe.ulima.edu.atisavi.repository.IUserRepository;
import pe.ulima.edu.atisavi.repository.MedicamentoRepo;
import pe.ulima.edu.atisavi.repository.MedicamentoSoliRepo;
import pe.ulima.edu.atisavi.repository.RecetaRepository;

@Controller 
@Log

public class RecetaController {
	
	

	@Autowired
    private RecetaRepository repository; 
	
	@Autowired
    private IUserRepository user; 
	
	@Autowired
	IUserService userservice;
	
	@Autowired
	MedicamentoRepo medicamentoRepo;
	
	@Autowired
	MedicamentoSoliRepo medicamentoSoliRepo;
	
	@GetMapping("/pacientereceta")
	public String Rec(Principal principal, Model model) { 
		    	final String loggedInUserName = principal.getName();
		    	 model.addAttribute("pacientes", user.findByEmail(loggedInUserName));
		    	 List<RecetaList> receta= new ArrayList<>();
		    	 repository.findAll().stream().filter(a-> a.getNamePac()
		    			 .equalsIgnoreCase(user.findByEmail(loggedInUserName).get().getFirstName()))
		     	.forEach(a-> receta.add(
		     			RecetaList.builder()
		     			.cantidad(a.getMedicamentoSoli().get(0).getQuantity())
		     			.creado(a.getCreateDate())
		     			.doctor(a.getNameDoc()) 
		     			.medicamento(a.getMedicamentoSoli().get(0).getMedicine().getName())
		     			.pickdate(a.getMedicamentoSoli().get(0).getPickDate())
		     			.build()
		     			));
		         model.addAttribute("recetas", receta );
		return "VistaPacRec"; 
	}
	@GetMapping("/pacientereceta/crear")
	public String Rec1() {   
		return "crearReceta"; 
		
	}
	
	
	
	@GetMapping("/receta/list")
    public String getAll(Model model, Principal principal){
    	log.info("USUARIO LOGEADO: " +  principal.getName()); 
    	log.info("USUARIO LOGEADO: " +  user.findByEmail(principal.getName()).get().getFirstName()); 
    	List<RecetaList> receta= new ArrayList<>();
    	repository.findAll().stream().filter(a-> a.getNameDoc().equalsIgnoreCase(
    			user.findByEmail(principal.getName()).get().getFirstName()))
    	.forEach(a-> receta.add(
    			RecetaList.builder().cantidad(a.getMedicamentoSoli().get(0).getQuantity())
    			.creado(a.getCreateDate())
    			.paciente(a.getNamePac())
    			.id(a.getId())
    			.medicamento(a.getMedicamentoSoli().get(0).getMedicine().getName())
    			.pickdate(a.getMedicamentoSoli().get(0).getPickDate())
    			.build()
    			));
        model.addAttribute("recetas", receta );
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
            		.medicamento(receta.getMedicamentoSoli().get(0).getMedicine().getName())
            		.cantidad(receta.getMedicamentoSoli().get(0).getQuantity())
            		.build());  
            		
        }else{
        	model.addAttribute("addReceta", true);
            model.addAttribute("recetas", new RecetaDto());
        }
        return "add_edit_receta";
    }

    @PostMapping("/receta/addEdit")
    public String insertOrUpdate(Model model, RecetaDto receta1,Principal principal) throws Exception{  
    	   Optional<Receta> receta1Optional = receta1.getIdentifier()==null?null:repository.findById(receta1.getIdentifier());
           Medicamento medi=medicamentoRepo.findByName(receta1.getMedicamento());
           if(receta1Optional!=null && receta1Optional.isPresent() && receta1.getIdentifier()!=null){   
            	medicamentoRepo.findByName(receta1.getMedicamento());
            	log.info(receta1Optional.get().toString());
            	Receta editReceta = receta1Optional.get();
            	editReceta.setId(receta1.getIdentifier());
                editReceta.setMedicamentoSoli(Arrays.asList(MedicamentoSolicitud
                		.builder()
                		.medicine(medi)
                		.pickDate(generarFecha(medi, receta1.getCantidad()))                		
                		.build())); 
                log.info(receta1Optional.get().toString());
                log.info(editReceta.toString());
                repository.save(editReceta);
            }else {
            	if(user.findById(receta1.getPaciente()).isPresent()) {
            		repository.save(Receta.builder()
                			.id(receta1.getIdentifier())
                			.nameDoc(user.findByEmail(principal.getName()).get().getFirstName())
                			.namePac(user.findById(receta1.getPaciente()).get().getFirstName())
                			.medicamentoSoli((Arrays.asList(MedicamentoSolicitud.builder()
                					.medicine(medi)
                					.quantity(receta1.getCantidad())
                					.pickDate(generarFecha(medi, receta1.getCantidad()))
                					.build())))            			
                			.build());
            	}else {
            	  throw new Exception();
            	}
             	
            	
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
	
	private LocalDateTime generarFecha(Medicamento medicamento, Integer cantidad) {
		if(medicamento.getStock()-cantidad<0) {
			medicamento.setStock(medicamento.getStock()-cantidad);
			medicamentoRepo.save(medicamento);
			return LocalDateTime.now().plusMonths(1L);
		}else {
			medicamento.setStock(medicamento.getStock()-cantidad);
			medicamentoRepo.save(medicamento);
			return LocalDateTime.now().plusWeeks(1L);
		}
		
	}
	
}
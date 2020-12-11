package pe.ulima.edu.atisavi.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import pe.ulima.edu.atisavi.model.Cita;
import pe.ulima.edu.atisavi.model.MedicamentoSolicitud; 

@Repository
public interface MedicamentoSoliRepo extends JpaRepository<MedicamentoSolicitud, Long>{

 	
}
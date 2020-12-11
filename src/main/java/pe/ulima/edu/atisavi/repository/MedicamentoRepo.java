package pe.ulima.edu.atisavi.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.ulima.edu.atisavi.model.Medicamento;
import pe.ulima.edu.atisavi.model.Receta;

@Repository
public interface MedicamentoRepo extends JpaRepository<Medicamento, Long>{

	Medicamento findByName(String medicamento);

		
}
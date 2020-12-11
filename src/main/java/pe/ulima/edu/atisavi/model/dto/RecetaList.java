package pe.ulima.edu.atisavi.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class RecetaList {

	Long id;
	String paciente;
	String doctor;
	String medicamento;
	LocalDateTime creado;
	LocalDateTime pickdate;
	Integer cantidad;
	
}

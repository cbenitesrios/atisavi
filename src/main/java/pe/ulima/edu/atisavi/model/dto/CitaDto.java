package pe.ulima.edu.atisavi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDto {

	 private Long id;
		/* 
	    private String nombreDoc;
	AÑADIR LUEGO
	    private String nombrePac;
	    private String fecha;
	*/
	    
	    private String medicamento;
	    private Integer cantidad;
	    
}
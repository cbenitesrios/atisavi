package pe.ulima.edu.atisavi.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecetaDto {
	
	private Long identifier;
	private String medicamento;
	private Long paciente;
	private Integer cantidad;

}

package pe.ulima.edu.atisavi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	
	private String name;
	private String lastname;
	private String phone;
	private boolean paciente;
	private boolean medico;	
	private String email;
	private String pass;
}	

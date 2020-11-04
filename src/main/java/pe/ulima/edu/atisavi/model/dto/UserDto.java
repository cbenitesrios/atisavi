package pe.ulima.edu.atisavi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long  identifier;
	 
    private String mail;

    private String pass;

    private String firstName;
    
    private String lastName;
    
    private String phone;

    private boolean enabled;
}

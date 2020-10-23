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

    private Long id;
	 
    private String email;

    private String password;

    private String firstName;
    
    private String lastName;
    
    private String phone;

    private boolean enabled;
}

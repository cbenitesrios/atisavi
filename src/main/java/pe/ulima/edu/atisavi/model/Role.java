package pe.ulima.edu.atisavi.model;  
 
import javax.persistence.Entity;  
import javax.persistence.Id; 
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

	@Id 
 	private Long id;
	private String rolename;  
	
}

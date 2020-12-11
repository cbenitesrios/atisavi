package pe.ulima.edu.atisavi.model;
  
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name =  "cita")
public class Cita {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private String medicamento;
    private Integer cantidad;
    
 
}
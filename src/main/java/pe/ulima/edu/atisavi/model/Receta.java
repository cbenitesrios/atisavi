package pe.ulima.edu.atisavi.model;
 
import java.util.Collection; 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.ManyToMany; 
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "receta")
public class Receta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
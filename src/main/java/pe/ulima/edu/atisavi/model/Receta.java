package pe.ulima.edu.atisavi.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp; 
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
	
	 
    private String nameDoc;  
    
    private String namePac;
    
    @Column(name="createDate", nullable = false)
	@CreationTimestamp
	private LocalDateTime createDate;
 
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  
    @JoinColumn(name = "receta_id", nullable = false)
    private  List<MedicamentoSolicitud> medicamentoSoli;
    
 
}
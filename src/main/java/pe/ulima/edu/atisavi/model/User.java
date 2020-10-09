package pe.ulima.edu.atisavi.model;
 
import java.util.List; 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.ManyToMany; 
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
@Table(name = "user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique = true)
    private String email;

    private String password;

    private String name;
    
    private String lastname;
    
    private String phone;

    private boolean enabled;

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY ) 
    private List<Role> role; 
 

}

package pe.ulima.edu.atisavi.business.impl; 

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.business.IUserService;
import pe.ulima.edu.atisavi.config.ConfigurationSecurity;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository; 

@Log
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository userrepo;
	 
	@Autowired
	ConfigurationSecurity encoder;
	
	@Override
	public boolean login(UserDto usuario) {
		
		User user = null;
		Optional<User> opc = userrepo.findByEmail(usuario.getEmail());
		if(opc.isPresent()) {
			user = opc.get();  
			log.info(user.toString() + " el usuario retornado.");
			return (encoder.passwordEncoder().matches(usuario.getPass(), user.getPassword()));
		}else {
			log.info("No se encontro paciente");
			return false;
		}
		
	}
	
	 
}

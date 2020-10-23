package pe.ulima.edu.atisavi.business.impl; 

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.java.Log;
import pe.ulima.edu.atisavi.business.IUserService; 
import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.model.User;
import pe.ulima.edu.atisavi.model.dto.UserDto;
import pe.ulima.edu.atisavi.repository.IUserRepository; 

@Log
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository userRepository;
	 
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		User user = userRepository.findByEmail(username).orElseThrow (()-> new UsernameNotFoundException ("Invalid username or password."));
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
		 
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
 
}

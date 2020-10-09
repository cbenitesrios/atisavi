package pe.ulima.edu.atisavi.business;

import pe.ulima.edu.atisavi.model.dto.UserDto;

public interface IUserService { 
	boolean login(UserDto user);
}

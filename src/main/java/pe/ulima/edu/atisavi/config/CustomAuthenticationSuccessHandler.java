package pe.ulima.edu.atisavi.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Configuration
@Log
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

		 Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		 log.info(roles.toString());
	        if (roles.contains("ADMIN")) {
	            httpServletResponse.sendRedirect("/admin/list");
	        } else if(roles.contains("PACIENTE")) {
	            httpServletResponse.sendRedirect("/paciente/");
	        }else {
	        	httpServletResponse.sendRedirect("/doctor/");
	        }
		
	}
}
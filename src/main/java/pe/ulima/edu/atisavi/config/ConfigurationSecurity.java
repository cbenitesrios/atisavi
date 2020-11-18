package pe.ulima.edu.atisavi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher; 
import pe.ulima.edu.atisavi.business.IUserService; 

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter{
	 
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(5);
	}
	 	 
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	
	@Autowired
    public ConfigurationSecurity(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

	
	
    protected void configure(HttpSecurity http) throws Exception {
    
    	 http
         .authorizeRequests()
             .antMatchers(
                     "/",
                     "/home",   
                     "/register/**", 
                     "/js/**", 
                     "/images/**", 
                     "/fonts/**", 
                     "/vendor/**", 
                     "/imagenes/**",
                     "/webjars/**",
                     "/css/**",
                     "/img/**").permitAll()
              .antMatchers("/admin/**").hasAuthority("ADMIN")
              .antMatchers("/doctor/**").hasAuthority("DOCTOR")
              .antMatchers("/paciente/**").hasAuthority("PACIENTE")
              .anyRequest().authenticated()
         .and() 
         	 .formLogin().permitAll()
         	 .loginPage("/login")
         	 .successHandler(authenticationSuccessHandler)
         	 //.defaultSuccessUrl("/admin/list")
             .usernameParameter("email").passwordParameter("password")
          .and()  
             .logout().permitAll()
         .and()
    	 .csrf().disable();
    }
    
    @Autowired
    IUserService userDetailsService; 
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService( userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    } 
  
}

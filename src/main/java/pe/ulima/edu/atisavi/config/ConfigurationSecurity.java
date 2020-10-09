package pe.ulima.edu.atisavi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
    	 http
         .authorizeRequests()
             .antMatchers(
                     "/",
                     "/home",
                     "/list",
                     "/add/**",
                     "/addEdit/**",
                     "/edit/**",
                     "/loginerror",    
                     "/loginok",  
                     "/register/**",
                     "/insesion/**",
                     "/js/**", 
                     "/images/**", 
                     "/fonts/**", 
                     "/vendor/**", 
                     "/imagenes/**",
                     "/webjars/**",
                     "/css/**",
                     "/img/**").permitAll()
             .antMatchers("/user/**").hasRole("USER")
             .antMatchers("/admin/**").hasRole("ADMIN")
             .anyRequest().authenticated()
         .and() 
         .exceptionHandling()
             .accessDeniedHandler(accessDeniedHandler);
    }
}

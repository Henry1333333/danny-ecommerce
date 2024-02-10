package pe.idat.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.idat.backend.service.impl.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/usuarios/agregar", "imagen/upload").permitAll()
                //Autorizacion para api de productos
    		    .antMatchers(HttpMethod.GET,"/producto/**").permitAll()
    		    .antMatchers(HttpMethod.GET,"/producto/encontrar/**").permitAll()
    		    .antMatchers(HttpMethod.POST,"/producto/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.PUT,"/producto/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.DELETE,"/producto/**").hasAnyAuthority("admin")
    		    
    		    //Autorizacion para api de categoria
    		    .antMatchers(HttpMethod.GET,"/categoria/**").hasAnyAuthority("cliente", "admin")
    		    .antMatchers(HttpMethod.GET,"/categoria/encontrar/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.POST,"/categoria/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.PUT,"/categoria/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.DELETE,"/categoria/**").hasAnyAuthority("admin")
    		  
    		    //Autorizacion para api de venta
    		    .antMatchers(HttpMethod.GET,"/venta/**").permitAll()
    		    .antMatchers(HttpMethod.GET,"/venta/listarPorId/**").permitAll()
    		    .antMatchers(HttpMethod.GET,"/venta/encontrar/**").permitAll()
    		    .antMatchers(HttpMethod.POST,"/venta/**").permitAll()
    		    .antMatchers(HttpMethod.PUT,"/venta/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.DELETE,"/venta/**").hasAnyAuthority("admin")
    		    
    		    //Autorizacion para api de detalleventa
    		    .antMatchers(HttpMethod.GET,"/detalleventa/**").permitAll()
    		    .antMatchers(HttpMethod.GET,"/detalleventa/encontrar/**").permitAll()
    		    .antMatchers(HttpMethod.POST,"/detalleventa/**").permitAll()
    		    .antMatchers(HttpMethod.PUT,"/detalleventa/**").permitAll()
    		    .antMatchers(HttpMethod.DELETE,"/detalleventa/**").hasAnyAuthority("admin")
    		    
    		    //Autorizacion para api de rol
    		    .antMatchers(HttpMethod.GET,"/rol/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.GET,"/rol/encontrar/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.POST,"/rol/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.PUT,"/rol/**").hasAnyAuthority("admin")
    		    .antMatchers(HttpMethod.DELETE,"/rol/**").hasAnyAuthority("admin")
    		    
    		    //autorización para cualquier usuario (cliente, admin)
                .antMatchers("/authenticate","/usuarios/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

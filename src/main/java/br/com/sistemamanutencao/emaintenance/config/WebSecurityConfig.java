package br.com.sistemamanutencao.emaintenance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sistemamanutencao.emaintenance.security.JwtAuthenticationEntryPoint;
import br.com.sistemamanutencao.emaintenance.security.JwtAuthenticationFilter;
import br.com.sistemamanutencao.emaintenance.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthenticationEntryPoint jwtEntryPoint;

	@Bean
	public JwtAuthenticationFilter authenticationJwtTokenFilter() {
		return new JwtAuthenticationFilter();
	}

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint jwtEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtEntryPoint = jwtEntryPoint;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
                "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
        		.and()
				.formLogin()
				.and()
				// sample anonymous customization
				.anonymous().authorities("ROLE_ANON")
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/favicon.ico", "/**/*.json", "/**/*.xml", "/**/*.properties", "/**/*.woff2", "/**/*.woff",
                				"/**/*.ttf", "/**/*.ttc", "/**/*.ico", "/**/*.bmp", "/**/*.png", "/**/*.gif", "/**/*.svg",
                				"/**/*.jpg", "/**/*.jpeg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/**/api/auth/**").permitAll()
                .antMatchers("/api/clientes/**").permitAll()
                .antMatchers("/api/equipamentos/**").permitAll()
                .antMatchers("/api/manutencoes/**").permitAll()
                .antMatchers("/api/fabricantes/**").permitAll()             
                .antMatchers("/api/criticidades/**").permitAll() 
                .antMatchers("/api/historico-equipamentos/**").permitAll()  
                .antMatchers("/api/historico-manutencoes/**").permitAll() 
                .antMatchers("/api/tipos-equipamentos/**").permitAll()      
                .antMatchers("/api/tipos-manutencoes/**").permitAll()       
                .antMatchers("/api/servicos-prestados/**").permitAll()
                .antMatchers("/api/users/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

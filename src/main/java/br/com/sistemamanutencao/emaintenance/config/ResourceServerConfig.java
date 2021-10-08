package br.com.sistemamanutencao.emaintenance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/usuarios").permitAll()
                .antMatchers(
                        "/api/clientes/**",
                        "/api/equipamentos/**",
                        "/api/manutencoes/**",
                        "/api/fabricantes/**",
                        "/api/criticidades/**",
                        "/api/historico-equipamentos/**",
                        "/api/historico-manutencoes/**",
                        "/api/tipos-equipamentos/**",
                        "/api/tipos-manutencoes/**",
                        "/api/servicos-prestados/**").authenticated()
                .anyRequest().denyAll();

        ;
    }
}

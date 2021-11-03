// NSJC&NSMS
package br.com.sistemamanutencao.emaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMaintenanceApplication.class, args);
    }
}

package br.com.sistemamanutencao.emaintenance.model.entity.facade;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}

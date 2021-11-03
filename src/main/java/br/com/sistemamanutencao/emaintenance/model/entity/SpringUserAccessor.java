package br.com.sistemamanutencao.emaintenance.model.entity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.UserAccessor;

public class SpringUserAccessor implements UserAccessor 
{
	@Override
    public User getUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (User) authentication.getPrincipal();
    }
}

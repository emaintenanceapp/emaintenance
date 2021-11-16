package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.sistemamanutencao.emaintenance.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	
//	ConfirmationToken findByConfirmationToken(String confirmationToken);
	
    
    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);
}


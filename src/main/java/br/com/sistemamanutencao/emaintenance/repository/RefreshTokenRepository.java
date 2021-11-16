package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamanutencao.emaintenance.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    @Override
    Optional<RefreshToken> findById(Integer id);

    Optional<RefreshToken> findByToken(String token);

}

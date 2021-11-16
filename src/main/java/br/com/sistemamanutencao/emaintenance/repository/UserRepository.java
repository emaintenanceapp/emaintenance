package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.dto.UserDTO;
import br.com.sistemamanutencao.emaintenance.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
	// Consulta apenas o cliente pelo id
	@Query(value = "SELECT u FROM User u WHERE u.email =:email")
	User findByEmail(@Param("email") String email);
    
    Boolean existsByEmail(String email);

	Optional<User> findByEmailIgnoreCase(String email);

}

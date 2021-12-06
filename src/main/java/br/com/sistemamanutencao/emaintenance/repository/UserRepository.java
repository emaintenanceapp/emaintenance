package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistemamanutencao.emaintenance.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
	// Consulta apenas o cliente pelo id
	@Query(value = "SELECT u FROM User u WHERE u.id =:usuarioId")
	public Optional<User> findById(@Param("usuarioId") Integer usuarioId);
	
	// Consulta apenas o cliente pelo email
	@Query(value = "SELECT u FROM User u WHERE u.email =:email")
	public User findByEmail(@Param("email") String email);
    
	public Boolean existsByEmail(String email);

	public Optional<User> findByEmailIgnoreCase(String email);

}

package br.com.sistemamanutencao.emaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	Boolean existsByCpf(String cpf);

    // Consulta apenas o cliente pelo id
	@Query(value = "SELECT c FROM Cliente c WHERE c.id =:clienteId")
	Cliente findClienteById(@Param("clienteId") Integer clienteId);

	// Consulta traz apenas os cadatrados pelo usuário corrente pelo id
	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.id =:userId")
	List<Cliente> findClientesByUserId(@Param("userId") Integer userId);

	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.email =:email")
	List<Cliente> findClientesByUserEmail(@Param("email") String email);
	
}

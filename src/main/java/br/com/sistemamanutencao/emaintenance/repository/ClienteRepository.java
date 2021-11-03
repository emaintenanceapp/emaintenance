package br.com.sistemamanutencao.emaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	Boolean existsByCpf(String cpf);

    // Consulta traz apenas os cadatrados pelo usuário corrente
	@Query(value = "SELECT e FROM Cliente e JOIN e.user u WHERE u.id =:userId")
	List<Cliente> findClientesByUser(@Param("userId") long userId);
	
}

package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;

@Repository
public interface ClienteRepository
		extends JpaRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente, Integer> {

	public Boolean existsByCpf(String cpf);

	// Consulta apenas o cliente pelo id
	@Query(value = "SELECT c FROM Cliente c WHERE c.id =:clienteId")
	public Optional<Cliente> findClienteById(@Param("clienteId") Integer clienteId);

	// Consulta traz apenas os cadatrados pelo usuário corrente pelo id
	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.id =:userId")
	public Page<Cliente> findClientesByUserId(@Param("userId") Integer userId, Pageable pageReq);

	default Page<Cliente> findClientesByUserId(User user, Pageable pageReq) {
		return findClientesByUserId(user.getId(), pageReq);
	}

	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.email =:email")
	public Page<Cliente> findClientesByUserEmail(@Param("email") String email, Pageable pageReq);

	default Page<Cliente> findClientesByUserEmail(User user, Pageable pageReq) {
		return findClientesByUserEmail(user.getEmail(), pageReq);
	}

	public Optional<Cliente> findById(Integer id);

//	Page<Cliente> findByNomeContaining(String nome, Pageable pageable);
	
	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
//	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.id =:userId and c.nome =:nome")
//	public Page<Cliente> findByNomeContaining(@Param("nome") String nome, Pageable pageReq);
//
//	default Page<Cliente> findByNomeContaining(Cliente cliente, Pageable pageReq) {
//		return findByNomeContaining(cliente.getNome(), pageReq);
//	}
	
	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.id =:userId")
	public Page<Cliente> findAll(@Param("userId") Integer userId, Pageable pageReq);
	
	default Page<Cliente> findAll(User user, Pageable pageReq) {
		return findAll(user.getId(), pageReq);
	}
	
	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
	@Query(value = "SELECT c FROM Cliente c JOIN c.user u WHERE u.id =:userId and c.nome like %:nome%")
	public Page<Cliente> findByNomeLike(@Param("nome") String nome, @Param("userId") Integer userId, Pageable pageReq);
	
	default Page<Cliente> findByNomeLike(Cliente cliente, User user, Pageable pageReq) {
		return findByNomeLike(cliente.getNome(), user.getId(), pageReq);
	}
	
}

package br.com.sistemamanutencao.emaintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.ServicoPrestado;

@Repository
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer>, PagingAndSortingRepository<ServicoPrestado, Integer> {

    @Query(" select s from ServicoPrestado s join s.cliente c " +
            " where upper( c.nome ) like upper( :nome ) and MONTH(s.data) =:mes    ")
    Page<ServicoPrestado> findByNomeAndMes(
            @Param("nome") String nome, @Param("mes") Integer mes, Integer integer, Pageable pageable);

    // Consulta traz apenas os Serviços Prestados cadatrados pelo usuário corrente pelo id
    @Query(value = "SELECT sp FROM ServicoPrestado sp JOIN sp.user u WHERE u.id =:userId")
    List<ServicoPrestado> findClientesByUserId(@Param("userId") Integer userId);
    
	public Optional<ServicoPrestado> findById(Integer id);

	public Boolean existsByDescricao(String cpf);

	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
	@Query(value = "SELECT sp FROM ServicoPrestado sp JOIN sp.user u WHERE u.id =:userId")
	public Page<ServicoPrestado> findAll(@Param("userId") Integer userId, Pageable pageReq);
	
	default Page<ServicoPrestado> findAll(User user, Pageable pageReq) {
		return findAll(user.getId(), pageReq);
	}
	
	// Consulta traz apenas os cadatrados pelo usuário corrente pelo email
//	@Query(value = "SELECT sp FROM ServicoPrestado sp JOIN sp.user u WHERE u.id =:userId and sp.nome like %:nome%")
//	public Page<ServicoPrestado> findByNomeLike(@Param("nome") String nome, @Param("userId") Integer userId, Pageable pageReq);
//	
//	default Page<ServicoPrestado> findByNomeLike(ServicoPrestado servicoPrestado, User user, Pageable pageReq) {
//		return findByNomeLike(servicoPrestado.getDescricao(), user.getId(), pageReq);
//	}
	
}

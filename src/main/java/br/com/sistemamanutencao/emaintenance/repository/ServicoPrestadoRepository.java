package br.com.sistemamanutencao.emaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistemamanutencao.emaintenance.model.entity.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

    @Query(" select s from ServicoPrestado s join s.cliente c " +
            " where upper( c.nome ) like upper( :nome ) and MONTH(s.data) =:mes    ")
    List<ServicoPrestado> findByNomeClienteAndMes(
            @Param("nome") String nome, @Param("mes") Integer mes);

    // Consulta traz apenas os Serviços Prestados cadatrados pelo usuário corrente pelo id
    @Query(value = "SELECT sp FROM ServicoPrestado sp JOIN sp.user u WHERE u.id =:userId")
    List<ServicoPrestado> findClientesByUserId(@Param("userId") Integer userId);
}

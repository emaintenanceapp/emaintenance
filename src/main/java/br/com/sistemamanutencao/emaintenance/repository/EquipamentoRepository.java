package br.com.sistemamanutencao.emaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.entity.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

	@Query(value = "select e from Equipamento e, User u where u.id =:userId")
	public List<Equipamento> findEquipamentoByUser(@Param("userId") Integer userId);
}

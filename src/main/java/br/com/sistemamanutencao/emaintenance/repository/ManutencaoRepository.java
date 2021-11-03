package br.com.sistemamanutencao.emaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.entity.Manutencao;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Integer> {

	@Query(value = "select m from Manutencao m, User u where u.id = :userId ")
	public List<Manutencao> findManutencaoByUser(@Param("userId") long userId);
}

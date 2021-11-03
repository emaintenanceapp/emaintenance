package br.com.sistemamanutencao.emaintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.entity.TipoManutencao;

@Repository
public interface TipoManutencaoRepository extends JpaRepository<TipoManutencao, Integer> {

}

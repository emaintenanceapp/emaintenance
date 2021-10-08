package br.com.sistemamanutencao.emaintenance.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.entity.HistoricoManutencao;

@Repository
public interface HistoricoManutencaoRepository extends JpaRepository<HistoricoManutencao, Integer> {

}

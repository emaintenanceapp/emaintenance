package br.com.sistemamanutencao.emaintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.entity.HistoricoEquipamento;

@Repository
public interface HistoricoEquipamentoRepository extends JpaRepository<HistoricoEquipamento, Integer> {

}

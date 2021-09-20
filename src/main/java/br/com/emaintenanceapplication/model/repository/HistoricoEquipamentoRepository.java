package br.com.emaintenanceapplication.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emaintenanceapplication.model.entity.HistoricoEquipamento;

@Repository
public interface HistoricoEquipamentoRepository extends JpaRepository<HistoricoEquipamento, Integer> {

}

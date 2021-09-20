package br.com.emaintenanceapplication.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emaintenanceapplication.model.entity.TipoManutencao;

@Repository
public interface TipoManutencaoRepository extends JpaRepository<TipoManutencao, Integer> {

}

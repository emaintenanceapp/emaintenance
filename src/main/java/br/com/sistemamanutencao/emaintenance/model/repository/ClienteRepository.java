package br.com.sistemamanutencao.emaintenance.model.repository;

import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

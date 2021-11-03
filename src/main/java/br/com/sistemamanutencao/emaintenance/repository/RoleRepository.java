package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemamanutencao.emaintenance.model.Role;
import br.com.sistemamanutencao.emaintenance.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

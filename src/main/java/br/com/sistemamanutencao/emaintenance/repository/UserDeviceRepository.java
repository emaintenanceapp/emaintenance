package br.com.sistemamanutencao.emaintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemamanutencao.emaintenance.model.RefreshToken;
import br.com.sistemamanutencao.emaintenance.model.UserDevice;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer> {

    @Override
    Optional<UserDevice> findById(Integer id);

    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

    Optional<UserDevice> findByUserId(Integer userId);
}

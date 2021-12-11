package br.com.sistemamanutencao.emaintenance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.exception.ResourceNotFoundException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.ServicoPrestado;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.ServicoPrestadoVO;
import br.com.sistemamanutencao.emaintenance.repository.ServicoPrestadoRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import lombok.var;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository servicoPrestadoRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	public ServicoPrestadoVO findById(Integer id) {
		var entity = servicoPrestadoRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Não foram encontrados registros para esse ID", "id", id));
		return ServicoPrestadoVO.create(entity);
	}
	
	public ServicoPrestadoVO create(ServicoPrestadoVO servicoPrestadoVO) {
		Optional<User> user = userRepository.findById(servicoPrestadoVO.getIdUsuario());
		ServicoPrestado servicoPrestado = ServicoPrestado.create(servicoPrestadoVO);
		servicoPrestado.setUser(user.get());
		ServicoPrestado clienteRetorno = servicoPrestadoRepository.save(servicoPrestado);
		return ServicoPrestadoVO.create(clienteRetorno);
	}

	public boolean existsByDescricao(String descricao) {
		return servicoPrestadoRepository.existsByDescricao(descricao);
	}
	
	public Page<ServicoPrestadoVO> findAll(User user, Pageable pageable) {
		Page<ServicoPrestado> page = servicoPrestadoRepository.findAll(user, pageable);
		return page.map(this::convertToServicoPrestadoVO);
	}
	
	public Page<ServicoPrestadoVO> findByNomeAndMes(String nome, Integer mes, User user, Pageable pageable) {
		Page<ServicoPrestado> page = servicoPrestadoRepository.findByNomeAndMes(nome, mes, user.getId(), pageable);
		return page.map(this::convertToServicoPrestadoVO);
	}
	
	
	
	
	
	

	private ServicoPrestadoVO convertToServicoPrestadoVO(ServicoPrestado servicoPrestado) {
		return ServicoPrestadoVO.create(servicoPrestado);
	}
	
	private ServicoPrestado convertToServicoPrestado(ServicoPrestadoVO servicoPrestadoVO) {
		return ServicoPrestado.create(servicoPrestadoVO);
	}

}

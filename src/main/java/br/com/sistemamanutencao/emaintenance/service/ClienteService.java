package br.com.sistemamanutencao.emaintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.exception.ResourceNotFoundException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.ClienteVO;
import br.com.sistemamanutencao.emaintenance.repository.ClienteRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import lombok.var;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UserRepository userRepository;

	public boolean existsByCpf(String cpf) {
		return clienteRepository.existsByCpf(cpf);
	}

	public ClienteVO create(ClienteVO clienteVO) {
		Optional<User> user = userRepository.findById(clienteVO.getIdUsuario());
		Cliente cliente = Cliente.create(clienteVO);
		cliente.setUser(user.get());
		Cliente clienteRetorno = clienteRepository.save(cliente);
		return ClienteVO.create(clienteRetorno);
	}

	public Page<ClienteVO> findAll(Pageable pageable) {
		var page = clienteRepository.findAll(pageable);
		return page.map(this::convertToClienteVO);
	}
	public Page<ClienteVO> findAll(User user, Pageable pageable) {
		var page = clienteRepository.findAll(user, pageable);
		return page.map(this::convertToClienteVO);
	}

	public Page<ClienteVO> findByNomeContaining(String nome, Pageable pageable) {
		var page = clienteRepository.findByNomeContaining(nome, pageable);
		return page.map(this::convertToClienteVO);
	}
	
	public Page<ClienteVO> findByNomeLike(String nome, User user, Pageable pageable) {
		Page<Cliente> page = clienteRepository.findByNomeLike(nome, user.getId(), pageable);
		return page.map(this::convertToClienteVO);
	}

//	public Optional<List<Cliente>> findClientesByUserId(Integer userId) {
//		var clientes = clienteRepository.findClientesByUserId(userId);
//		if (clientes != null) {
//			return clientes; 
//		}else {
//			throw new ClienteUserException(userId);
//		}
//	}

//	public Optional<List<ClienteVO>> findClientesByUserId(int page, int size, String sortDir, String sort, Integer userId) {
//	 
//	    PageRequest pageReq
//	     = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
//	 
//	    Optional<List<Cliente>> clientes = clienteRepository
//	      .findClientesByUserId(userId, pageReq);
//	    return posts.getContent();
//	}
//	
//	public List<ClienteVO> findClientesByUserEmail(int page, int size, String sortDir, String sort, String userEmail) {
//		
//		PageRequest pageReq
//		= PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
//		
//		Optional<List<Cliente>> clientes = clienteRepository
//				.findClientesByUserEmail(userEmail, pageReq);
//		return clientes.getContent();
//	}

	public List<Cliente> findClientesByUserEmail(int page, int size, String sortDir, String sort, String email) {

		PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

		Page<Cliente> posts = clienteRepository.findClientesByUserEmail(email, pageReq);
		return posts.getContent();
	}

	public List<Cliente> findClientesByUserId(int page, int size, String sortDir, String sort, Integer id) {

		PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

		Page<Cliente> posts = clienteRepository.findClientesByUserId(id, pageReq);
		return posts.getContent();
	}

//	public Optional<List<Cliente>> findClientesByUserEmail(String userEmail) {
//		var clientes = clienteRepository.findClientesByUserEmail(userEmail);
//		if (clientes != null) {
//			return clientes; 
//		}else {
//			throw new ClienteUserException("Username " + userEmail + " not found!");
//		}
//	}

	public ClienteVO findById(Integer id) {
		var entity = clienteRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Não foram encontrados registros para esse ID", "id", id));
		return ClienteVO.create(entity);
	}

	public ClienteVO update(User user, ClienteVO clienteVO) {
		
		final Optional<Cliente> optionalCliente = clienteRepository.findById(clienteVO.getId());

		if (!optionalCliente.isPresent()) {
			new ResourceNotFoundException("Cliente não existe na base de dados!", null, optionalCliente);
		}
		Cliente cliente = Cliente.create(clienteVO);
		cliente.setUser(user);
		Cliente clienteRetorno = clienteRepository.save(cliente);
		return ClienteVO.create(clienteRetorno);
	}

	public void delete(User user,Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Não foram encontrados registros para esse ID", "id", id));
		cliente.setUser(user);
		clienteRepository.delete(cliente);
	}

	private ClienteVO convertToClienteVO(Cliente cliente) {
		return ClienteVO.create(cliente);
	}
	
	private Cliente convertToCliente(ClienteVO clienteVO) {
		return Cliente.create(clienteVO);
	}
}

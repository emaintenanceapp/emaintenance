package br.com.sistemamanutencao.emaintenance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
	
	public boolean existsByCpf(String cpf) {
		return repository.existsByCpf(cpf);
	}
	
    public List<Cliente> findClientesByUserId(Integer userId) {
		List<Cliente> clientes = repository.findClientesByUserId(userId);
		return clientes.stream().collect(Collectors.toList());
    }
    
    public List<Cliente> findClientesByUserEmail(String userEmail) {
    	List<Cliente> clientes = repository.findClientesByUserEmail(userEmail);
    	return clientes.stream().collect(Collectors.toList());
    }
}

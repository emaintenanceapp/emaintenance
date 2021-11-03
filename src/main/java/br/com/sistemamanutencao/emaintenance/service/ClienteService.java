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
	
    public List<Cliente> findClientesByuser(Long userId) {
		List<Cliente> clientes = repository.findClientesByUser(userId);
		return clientes.stream().collect(Collectors.toList());
    }
}

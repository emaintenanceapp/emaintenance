package br.com.sistemamanutencao.emaintenance.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistemamanutencao.emaintenance.exception.ClienteCadastradoException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.repository.ClienteRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
    private final ClienteRepository repository;
    private final UserRepository userRepository;
    
    @Autowired
    public ClienteController(ClienteRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PostMapping(value = "/{usuarioLogado}")
    public Cliente salvar( @RequestBody @Valid Cliente cliente, @PathVariable(value = "usuarioLogado") final String usuarioLogado){
    	
		User user = userRepository.findByEmail(usuarioLogado);
    	
    	if (user.getActive()) {
    		cliente.setCpf(cliente.getCpf().replaceAll("\\D", StringUtils.EMPTY));
    		
    		boolean exists = repository.existsByCpf(cliente.getCpf());
    		if(exists){
    			throw new ClienteCadastradoException(cliente.getCpf());
    		}
    		
    		cliente.activate();
    		cliente.setUser(user);
			log.info("Cliente salvo com sucesso! " + cliente.getId());
            return repository.save(cliente);
    	}
    	return null;
    }

    // Consulta traz apenas os cadatrados pelo usuário corrente
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/{userId}")
	public List<Cliente> findClientesByUser(@PathVariable Integer userId) {		
		List<Cliente> clientes = repository.findClientesByUserId(userId);		
		return clientes;
	}
    
    // Consulta traz apenas os cadatrados pelo usuário corrente
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @GetMapping("/cliente/{clienteId}")
    public Cliente findClienteById(@PathVariable Integer clienteId) {		
    	Cliente cliente = repository.findClienteById(clienteId);		
    	return cliente;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @DeleteMapping("{id}")
    public void deletar( @PathVariable Integer id ){
        repository
            .findById(id)
            .map( cliente -> {
                repository.delete(cliente);
                return Void.TYPE;
            })
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PutMapping(value = "/{usuarioLogado}/{id}")
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado, @PathVariable(value = "usuarioLogado") final String usuarioLogado ) {
		User user = userRepository.findByEmail(usuarioLogado);
        boolean exists = repository.existsByCpf(clienteAtualizado.getCpf());
        if(exists){
            throw new ClienteCadastradoException(clienteAtualizado.getCpf());
        }
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
            		cliente.setUser(user);
        			log.info("Cliente atualizado com sucesso! " + cliente.getId());
                    return repository.save(cliente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }
}

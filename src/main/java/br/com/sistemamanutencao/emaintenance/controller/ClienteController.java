// NSJC&NSMS
package br.com.sistemamanutencao.emaintenance.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import br.com.sistemamanutencao.emaintenance.model.CurrentUserAccessor;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.repository.ClienteRepository;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	

    private final ClienteRepository repository;

    CurrentUserAccessor currentUser = new CurrentUserAccessor(); 
    
    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    public Cliente salvar( @RequestBody @Valid Cliente cliente, @AuthenticationPrincipal User user ){
    	
    	cliente.setCpf(cliente.getCpf().replaceAll("\\D", StringUtils.EMPTY));
    	
        boolean exists = repository.existsByCpf(cliente.getCpf());
        if(exists){
            throw new ClienteCadastradoException(cliente.getCpf());
        }
        
//        User user = currentUser.getUser();
//        SecurityContext sc = SecurityContextHolder.getContext();
//        System.out.println("Logged User Name: "+sc.getAuthentication().getName());
//        System.out.println("Logged User Principal: "+sc.getAuthentication().getPrincipal());
//        System.out.println("Logged User Credentials: "+sc.getAuthentication().getCredentials());
//        System.out.println("Logged User Details: "+sc.getAuthentication().getDetails());
//        System.out.println("Logged User Authorities: "+sc.getAuthentication().getAuthorities());
        System.out.println("Logged User loggin dfdfd: "+user.getEmail());
        
        cliente.activate();
        cliente.setUser(user);
        return repository.save(cliente);
    }

//    @GetMapping("{id}")
//    public Cliente acharPorId( @PathVariable Integer id ){
//        return repository
//                .findById(id)
//                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
//    }
    
    
    // Consulta traz apenas os cadatrados pelo usuário corrente
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/{userId}")
	public List<Cliente> findClientesByUser(@PathVariable Long userId) {		
		List<Cliente> clientes = repository.findClientesByUser(userId);		
		return clientes;
	}

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id ){
        repository
            .findById(id)
            .map( cliente -> {
                repository.delete(cliente);
                return Void.TYPE;
            })
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Cliente clienteAtualizado ) {
        boolean exists = repository.existsByCpf(clienteAtualizado.getCpf());
        if(exists){
            throw new ClienteCadastradoException(clienteAtualizado.getCpf());
        }
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(cliente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }
    
//    @RequestMapping("/novo")
//	public ModelAndView novo(Usuario usuario, @AuthenticationPrincipal UsuarioSistema usuarioLogado, HttpSession session) {
//		ModelAndView mv = util.validarLicenca(usuarioLogado, session, "usuario/CadastroUsuario");
//		mv.addObject("empresas", empresas.findAll());
//		mv.addObject("grupos", grupos.gruposUsuarioLogado(usuarioLogado.getUsuario()));
//		return mv;
//	}
}

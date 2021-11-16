package br.com.sistemamanutencao.emaintenance.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

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

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Equipamento;
import br.com.sistemamanutencao.emaintenance.repository.EquipamentoRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping(value = "/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

	private EquipamentoRepository equipamentoRepository;
    private final UserRepository userRepository;

	@ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@PostMapping(value = "/{usuarioLogado}")
	public Equipamento salvar(@RequestBody @Valid Equipamento equipamento, @PathVariable(value = "usuarioLogado") final String usuarioLogado) {
		User user = userRepository.findByEmail(usuarioLogado);
		equipamento.setUser(user);
		log.info("Equipamento salvo com sucesso! " + equipamento.getId());
		return equipamentoRepository.save(equipamento);
	}
	
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @GetMapping
	public List<Equipamento> listar() {
		return equipamentoRepository.findAll();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Integer id) {
		equipamentoRepository
				.findById(id)
				.map(equipamento -> {
					equipamentoRepository.delete(equipamento);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PutMapping("{id}")
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Equipamento equipamentoAtualizado, 
                           @PathVariable(value = "usuarioLogado") final String usuarioLogado ) throws Exception {
		User user = userRepository.findByEmail(usuarioLogado);

    	if (id == equipamentoAtualizado.getId()) {
    		equipamentoRepository
    		.findById(id)
    		.map( equipamento -> {
    			equipamento.setId(equipamentoAtualizado.getId());
    			equipamento.setDataAtualizacao(LocalDate.now());
    			equipamento.setUser(user);
				log.info("Equipamento atualizado com sucesso! " + equipamento.getId());
    			return equipamentoRepository.save(equipamento);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }
	
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/{usuarioId}")
	public List<Equipamento> findEquipamentoByUser(@PathVariable Integer userId) {		
		List<Equipamento> equipamentos = equipamentoRepository.findEquipamentoByUser(userId);		
		return equipamentos;
	}

}

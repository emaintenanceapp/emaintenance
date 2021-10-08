package br.com.sistemamanutencao.emaintenance.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.sistemamanutencao.emaintenance.model.entity.Criticidade;
import br.com.sistemamanutencao.emaintenance.model.repository.CriticidadeRepository;

@RestController
@RequestMapping("/api/criticidades")
public class CriticidadeController {
	
	private CriticidadeRepository criticidadeRepository;

	@Autowired
	public CriticidadeController(CriticidadeRepository criticidadeRepository) {
		this.criticidadeRepository = criticidadeRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Criticidade salvar(@RequestBody @Valid Criticidade criticidade) {
		return criticidadeRepository.save(criticidade);
	}
	
	@GetMapping("{id}")
	public Criticidade buscarPorId(@PathVariable Integer id) {
		return criticidadeRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Criticidade> listar() {
		return criticidadeRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		criticidadeRepository
				.findById(id)
				.map(criticidade -> {
					criticidadeRepository.delete(criticidade);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Criticidade criticidadeAtualizado ) throws Exception {
    	if (id == criticidadeAtualizado.getId()) {
    		criticidadeRepository
    		.findById(id)
    		.map( criticidade -> {
    			criticidade.setId(criticidadeAtualizado.getId());
    			return criticidadeRepository.save(criticidadeAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }

}

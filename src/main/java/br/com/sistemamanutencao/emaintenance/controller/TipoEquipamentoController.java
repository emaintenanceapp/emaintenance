package br.com.sistemamanutencao.emaintenance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.sistemamanutencao.emaintenance.model.entity.TipoEquipamento;
import br.com.sistemamanutencao.emaintenance.repository.TipoEquipamentoRepository;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/tipos-equipamentos")
public class TipoEquipamentoController {
	
	private TipoEquipamentoRepository tipoEquipamentoRepository;

	@Autowired
	public TipoEquipamentoController(TipoEquipamentoRepository tipoEquipamentoRepository) {
		this.tipoEquipamentoRepository = tipoEquipamentoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoEquipamento salvar(@RequestBody @Valid TipoEquipamento equipamento) {
		return tipoEquipamentoRepository.save(equipamento);
	}
	
	@GetMapping("{id}")
	public TipoEquipamento buscarPorId(@PathVariable Integer id) {
		return tipoEquipamentoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<TipoEquipamento> listar() {
		return tipoEquipamentoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		tipoEquipamentoRepository
				.findById(id)
				.map(equipamento -> {
					tipoEquipamentoRepository.delete(equipamento);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid TipoEquipamento tipoEquipamento) throws Exception {
    	if (id == tipoEquipamento.getId()) {
    		tipoEquipamentoRepository
    		.findById(id)
    		.map( equipamento -> {
    			equipamento.setId(tipoEquipamento.getId());
    			return tipoEquipamentoRepository.save(tipoEquipamento);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }

}

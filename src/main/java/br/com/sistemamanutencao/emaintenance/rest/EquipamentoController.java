package br.com.sistemamanutencao.emaintenance.rest;

import java.time.LocalDate;
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

import br.com.sistemamanutencao.emaintenance.model.entity.Equipamento;
import br.com.sistemamanutencao.emaintenance.model.repository.EquipamentoRepository;

@RestController
@RequestMapping(value = "/api/equipamentos")
public class EquipamentoController {

	private EquipamentoRepository equipamentoRepository;

	@Autowired
	public EquipamentoController(EquipamentoRepository equipamentoRepository) {
		this.equipamentoRepository = equipamentoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Equipamento salvar(@RequestBody @Valid Equipamento equipamento) {
		return equipamentoRepository.save(equipamento);
	}
	
	@GetMapping
	public List<Equipamento> listar() {
		return equipamentoRepository.findAll();
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		equipamentoRepository
				.findById(id)
				.map(equipamento -> {
					equipamentoRepository.delete(equipamento);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Equipamento equipamentoAtualizado ) throws Exception {
    	if (id == equipamentoAtualizado.getId()) {
    		equipamentoRepository
    		.findById(id)
    		.map( equipamento -> {
    			equipamento.setId(equipamentoAtualizado.getId());
    			equipamento.setDataAtualizacao(LocalDate.now());
    			return equipamentoRepository.save(equipamentoAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }

}

package br.com.emaintenanceapplication.rest;

import java.time.LocalDate;
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

import br.com.emaintenanceapplication.model.entity.HistoricoEquipamento;
import br.com.emaintenanceapplication.model.repository.HistoricoEquipamentoRepository;

@RestController
@RequestMapping("/api/historico-equipamentos")
@CrossOrigin("http://localhost:4200")
public class HistoricoEquipamentoController {
	
	private HistoricoEquipamentoRepository historicoEquipamentoRepository;

	@Autowired
	public HistoricoEquipamentoController(HistoricoEquipamentoRepository historicoEquipamentoRepository) {
		this.historicoEquipamentoRepository = historicoEquipamentoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HistoricoEquipamento salvar(@RequestBody @Valid HistoricoEquipamento historicoEquipamento) {
		return historicoEquipamentoRepository.save(historicoEquipamento);
	}
	
	@GetMapping("{id}")
	public HistoricoEquipamento buscarPorId(@PathVariable Integer id) {
		return historicoEquipamentoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<HistoricoEquipamento> listar() {
		return historicoEquipamentoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		historicoEquipamentoRepository
				.findById(id)
				.map(historicoEquipamento -> {
					historicoEquipamentoRepository.delete(historicoEquipamento);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid HistoricoEquipamento historicoEquipamentoAtualizado ) throws Exception {
    	if (id == historicoEquipamentoAtualizado.getId()) {
    		historicoEquipamentoRepository
    		.findById(id)
    		.map( historicoEquipamento -> {
    			historicoEquipamento.setId(historicoEquipamentoAtualizado.getId());
    			historicoEquipamento.setDataAtualizacao(LocalDate.now());
    			return historicoEquipamentoRepository.save(historicoEquipamentoAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }
}

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

import br.com.sistemamanutencao.emaintenance.model.entity.HistoricoManutencao;
import br.com.sistemamanutencao.emaintenance.model.repository.HistoricoManutencaoRepository;

@RestController
@RequestMapping("/api/historico-manutencoes")
public class HistoricoManutencaoController {

	private HistoricoManutencaoRepository historicoManutencaoRepository;

	@Autowired
	public HistoricoManutencaoController(HistoricoManutencaoRepository historicoManutencaoRepository) {
		this.historicoManutencaoRepository = historicoManutencaoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HistoricoManutencao salvar(@RequestBody @Valid HistoricoManutencao historicoManutencao) {
		return historicoManutencaoRepository.save(historicoManutencao);
	}
	
	@GetMapping("{id}")
	public HistoricoManutencao buscarPorId(@PathVariable Integer id) {
		return historicoManutencaoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<HistoricoManutencao> listar() {
		return historicoManutencaoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		historicoManutencaoRepository
				.findById(id)
				.map(historicoManutencao -> {
					historicoManutencaoRepository.delete(historicoManutencao);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid HistoricoManutencao historicoManutencaoAtualizado ) throws Exception {
    	if (id == historicoManutencaoAtualizado.getId()) {
    		historicoManutencaoRepository
    		.findById(id)
    		.map( equipamento -> {
    			equipamento.setId(historicoManutencaoAtualizado.getId());
    			equipamento.setDataAtualizacao(LocalDate.now());
    			return historicoManutencaoRepository.save(historicoManutencaoAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }
}

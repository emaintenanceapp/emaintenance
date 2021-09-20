package br.com.emaintenanceapplication.rest;

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

import br.com.emaintenanceapplication.model.entity.TipoManutencao;
import br.com.emaintenanceapplication.model.repository.TipoManutencaoRepository;

@RestController
@RequestMapping("/api/tipos-manutencoes")
@CrossOrigin("http://localhost:4200")
public class TipoManutencaoController {

	
	private TipoManutencaoRepository tipoManutencaoRepository;

	@Autowired
	public TipoManutencaoController(TipoManutencaoRepository tipoManutencaoRepository) {
		this.tipoManutencaoRepository = tipoManutencaoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoManutencao salvar(@RequestBody @Valid TipoManutencao tipoTipoManutencao) {
		return tipoManutencaoRepository.save(tipoTipoManutencao);
	}
	
	@GetMapping("{id}")
	public TipoManutencao buscarPorId(@PathVariable Integer id) {
		return tipoManutencaoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<TipoManutencao> listar() {
		return tipoManutencaoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		tipoManutencaoRepository
				.findById(id)
				.map(tipoTipoManutencao -> {
					tipoManutencaoRepository.delete(tipoTipoManutencao);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid TipoManutencao tipoManutencaoAtualizado ) throws Exception {
    	if (id == tipoManutencaoAtualizado.getId()) {
    		tipoManutencaoRepository
    		.findById(id)
    		.map( tipoManutencao -> {
    			tipoManutencao.setId(tipoManutencaoAtualizado.getId());
    			return tipoManutencaoRepository.save(tipoManutencaoAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }
}
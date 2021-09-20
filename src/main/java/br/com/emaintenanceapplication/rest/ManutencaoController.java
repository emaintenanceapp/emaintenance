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

import br.com.emaintenanceapplication.model.entity.Manutencao;
import br.com.emaintenanceapplication.model.repository.ManutencaoRepository;

@RestController
@RequestMapping("/api/manutencoes")
@CrossOrigin("http://localhost:4200")
public class ManutencaoController {
	
	private ManutencaoRepository manutencaoRepository;

	@Autowired
	public ManutencaoController(ManutencaoRepository manutencaoRepository) {
		this.manutencaoRepository = manutencaoRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Manutencao salvar(@RequestBody @Valid Manutencao manutencao) {
		return manutencaoRepository.save(manutencao);
	}
	
	@GetMapping("{id}")
	public Manutencao buscarPorId(@PathVariable Integer id) {
		return manutencaoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Manutencao> listar() {
		return manutencaoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		manutencaoRepository
				.findById(id)
				.map(manutencao -> {
					manutencaoRepository.delete(manutencao);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Manutencao manutencaoAtualizado) throws Exception {
    	if (id == manutencaoAtualizado.getId()) {
    		manutencaoRepository
    		.findById(id)
    		.map( manutencao -> {
    			manutencao.setId(manutencaoAtualizado.getId());
    			manutencao.setDataAtualizacao(LocalDate.now());
    			return manutencaoRepository.save(manutencaoAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }

}

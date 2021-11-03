package br.com.sistemamanutencao.emaintenance.controller;

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

import br.com.sistemamanutencao.emaintenance.model.entity.Fabricante;
import br.com.sistemamanutencao.emaintenance.repository.FabricanteRepository;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {
	
	private FabricanteRepository fabricanteRepository;

	@Autowired
	public FabricanteController(FabricanteRepository fabricanteRepository) {
		this.fabricanteRepository = fabricanteRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fabricante salvar(@RequestBody @Valid Fabricante tipoFabricante) {
		return fabricanteRepository.save(tipoFabricante);
	}
	
	@GetMapping("{id}")
	public Fabricante buscarPorId(@PathVariable Integer id) {
		return fabricanteRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Fabricante> listar() {
		return fabricanteRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		fabricanteRepository
				.findById(id)
				.map(tipoFabricante -> {
					fabricanteRepository.delete(tipoFabricante);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Fabricante fabricanteAtualizado ) throws Exception {
    	if (id == fabricanteAtualizado.getId()) {
    		fabricanteRepository
    		.findById(id)
    		.map( fabricante -> {
    			fabricante.setId(fabricanteAtualizado.getId());
    			fabricante.setDataAtualizacao(LocalDate.now());
    			return fabricanteRepository.save(fabricanteAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "fabricante não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }

}

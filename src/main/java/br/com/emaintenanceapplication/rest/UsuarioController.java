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

import br.com.emaintenanceapplication.model.entity.Usuario;
import br.com.emaintenanceapplication.model.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("http://localhost:4200")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario equipamento) {
		return usuarioRepository.save(equipamento);
	}
	
	@GetMapping("{id}")
	public Usuario buscarPorId(@PathVariable Integer id) {
		return usuarioRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		usuarioRepository
				.findById(id)
				.map(equipamento -> {
					usuarioRepository.delete(equipamento);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Usuario equipamentoAtualizado) throws Exception {
    	if (id == equipamentoAtualizado.getId()) {
    		usuarioRepository
    		.findById(id)
    		.map( usuario -> {
    			usuario.setId(equipamentoAtualizado.getId());
    			usuario.setDataAtualizacao(LocalDate.now());
    			return usuarioRepository.save(equipamentoAtualizado);
    		})
    		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado") );
		}else {
			throw new Exception("Erro groceiro");
		}
    }
}

package br.com.sistemamanutencao.emaintenance.rest;

import br.com.sistemamanutencao.emaintenance.exception.UsuarioCadastradoException;
import br.com.sistemamanutencao.emaintenance.model.entity.Usuario;
import br.com.sistemamanutencao.emaintenance.model.repository.UsuarioRepository;
import br.com.sistemamanutencao.emaintenance.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try{
            service.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }
}

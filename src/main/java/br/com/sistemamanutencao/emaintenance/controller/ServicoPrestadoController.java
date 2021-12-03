package br.com.sistemamanutencao.emaintenance.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistemamanutencao.emaintenance.exception.ClienteCadastradoException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.model.entity.ServicoPrestado;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.ServicoPrestadoVO;
import br.com.sistemamanutencao.emaintenance.repository.ClienteRepository;
import br.com.sistemamanutencao.emaintenance.repository.ServicoPrestadoRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final UserRepository userRepository;
	private final ServicoPrestadoRepository servicoPrestadoRepository;
	
	private ServicoPrestadoVO servicoPrestadoDTO = new ServicoPrestadoVO();
	
	private List<ServicoPrestadoVO> listaServicosPrestadosDTO = new ArrayList<ServicoPrestadoVO>();

	@ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PostMapping(value = "/{usuarioLogado}")
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoVO dto, @PathVariable(value = "usuarioLogado") final String usuarioLogado) {
		User user = userRepository.findByEmail(usuarioLogado);
    	
    	if (user.getActive()) {
    		LocalDate data = dto.getData();
    		Integer idCliente = dto.getCliente().getId();
    		Cliente cliente = clienteRepository.findById(idCliente)
    				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

    		ServicoPrestado servicoPrestado = new ServicoPrestado();
    		servicoPrestado.setDescricao(dto.getDescricao());
    		servicoPrestado.setData(data);
    		servicoPrestado.setCliente(cliente);
    		servicoPrestado.setPreco(dto.getPreco());
    		servicoPrestado.setUser(user);
    		log.info("Serviço Prestado salvo com sucesso! " + servicoPrestado.getId());
    		return servicoPrestadoRepository.save(servicoPrestado);
    	}
    	return null;
	}

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//	@GetMapping
//	public List<ServicoPrestado> pesquisar(
//			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
//			@RequestParam(value = "mes", required = false) Integer mes) {
//		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
//    	extractedServicoPrestado(listaServicosPrestados, servicoPrestadoDTO, dto);
//		return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
//	}

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/pesquisar")
	public List<ServicoPrestadoVO> pesquisarDTO(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {
		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
		return listaServicosPrestadosDTO;
	}
	
    // Consulta traz apenas os cadatrados pelo usuário corrente
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/{userId}")
	public List<ServicoPrestadoVO> findClientesByUser(@PathVariable Integer userId) {		
		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoRepository.findClientesByUserId(userId);		
		return listaServicosPrestadosDTO;
	}

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/servico-prestado/{id}")
	public ServicoPrestadoVO acharPorId(@PathVariable Integer id) {
    	ServicoPrestado servicoPrestado = servicoPrestadoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
		return servicoPrestadoDTO;
	}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@DeleteMapping("{id}")
	public void deletar(@PathVariable Integer id) {
		servicoPrestadoRepository.findById(id).map(servicoPrestado -> {
			servicoPrestadoRepository.delete(servicoPrestado);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PutMapping(value = "/{usuarioLogado}/{id}")
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoPrestado servicoPrestadoAtualizado, @PathVariable(value = "usuarioLogado") final String usuarioLogado ) {
		User user = userRepository.findByEmail(usuarioLogado);
		
    	if (user.getActive()) {
    		servicoPrestadoRepository.findById(id).map(servicoPrestado -> {
//    			servicoPrestado.setCliente(servicoPrestadoAtualizado.getCliente());
//    			servicoPrestado.setDescricao(servicoPrestadoAtualizado.getDescricao());
//    			servicoPrestado.setData(servicoPrestadoAtualizado.getData());
//    			servicoPrestado.setPreco(servicoPrestadoAtualizado.getPreco());
//    			servicoPrestado.setUser(user);
    			log.info("Serviço Prestado atualizado com sucesso! " + servicoPrestado.getId());
    			return servicoPrestadoRepository.save(servicoPrestado);
    		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
    	}
	}
	
}

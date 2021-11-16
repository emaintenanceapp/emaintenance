package br.com.sistemamanutencao.emaintenance.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import br.com.sistemamanutencao.emaintenance.dto.ServicoPrestadoDTO;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.model.entity.ServicoPrestado;
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

	@ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @PostMapping(value = "/{usuarioLogado}")
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto, @PathVariable(value = "usuarioLogado") final String usuarioLogado) {
    	
		User user = userRepository.findByEmail(usuarioLogado);
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();

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

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {
		return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/pesquisarDTO")
	public List<ServicoPrestadoDTO> pesquisarDTO(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {
		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
		ServicoPrestadoDTO servicoPrestadoDTO = new ServicoPrestadoDTO();
		List<ServicoPrestadoDTO> dto = new ArrayList<ServicoPrestadoDTO>();
		for (ServicoPrestado sp : listaServicosPrestados) {
			servicoPrestadoDTO.setNomeCliente(sp.getCliente().getNome());
			servicoPrestadoDTO.setData(sp.getData().toString());
			servicoPrestadoDTO.setIdCliente(sp.getCliente().getId());
			servicoPrestadoDTO.setPreco(sp.getPreco());
			servicoPrestadoDTO.setDescricao(sp.getDescricao());
			dto.add(servicoPrestadoDTO);
		}
		return dto;
	}
	
    // Consulta traz apenas os cadatrados pelo usuário corrente
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/{userId}")
	public List<ServicoPrestado> findClientesByUser(@PathVariable Integer userId) {		
		List<ServicoPrestado> servicoPrestados = servicoPrestadoRepository.findClientesByUserId(userId);		
		return servicoPrestados;
	}

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/servico-prestado/{id}")
	public ServicoPrestado acharPorId(@PathVariable Integer id) {
		return servicoPrestadoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
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
		servicoPrestadoRepository.findById(id).map(servicoPrestado -> {
			servicoPrestado.setCliente(servicoPrestadoAtualizado.getCliente());
			servicoPrestado.setDescricao(servicoPrestadoAtualizado.getDescricao());
			servicoPrestado.setData(servicoPrestadoAtualizado.getData());
			servicoPrestado.setPreco(servicoPrestadoAtualizado.getPreco());
			servicoPrestado.setUser(user);
			log.info("Serviço Prestado atualizado com sucesso! " + servicoPrestado.getId());
			return servicoPrestadoRepository.save(servicoPrestado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}
}

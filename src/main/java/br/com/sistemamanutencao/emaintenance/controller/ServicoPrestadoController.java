package br.com.sistemamanutencao.emaintenance.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.model.entity.ServicoPrestado;
import br.com.sistemamanutencao.emaintenance.repository.ClienteRepository;
import br.com.sistemamanutencao.emaintenance.repository.ServicoPrestadoRepository;
import br.com.sistemamanutencao.emaintenance.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();

		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setPreco(dto.getPreco());

		return repository.save(servicoPrestado);
	}

	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {
		return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}

	@GetMapping("/pesquisarDTO")
	public List<ServicoPrestadoDTO> pesquisarDTO(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes) {
		List<ServicoPrestado> listaServicosPrestados = repository.findByNomeClienteAndMes("%" + nome + "%", mes);
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
//
//	@GetMapping
//	public List<ServicoPrestado> obterTodos() {
//		return repository.findAll();
//	}

	@GetMapping("{id}")
	public ServicoPrestado acharPorId(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id).map(servicoPrestado -> {
			repository.delete(servicoPrestado);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoPrestado servicoPrestadoAtualizado) {
		repository.findById(id).map(servicoPrestado -> {
			servicoPrestado.setCliente(servicoPrestadoAtualizado.getCliente());
			servicoPrestado.setDescricao(servicoPrestadoAtualizado.getDescricao());
			servicoPrestado.setData(servicoPrestadoAtualizado.getData());
			servicoPrestado.setPreco(servicoPrestadoAtualizado.getPreco());
			return repository.save(servicoPrestado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
	}
}

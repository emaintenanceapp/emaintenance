package br.com.sistemamanutencao.emaintenance.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemamanutencao.emaintenance.exception.ClienteCadastradoException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.ClienteVO;
import br.com.sistemamanutencao.emaintenance.service.ClienteService;
import br.com.sistemamanutencao.emaintenance.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "${app.security.cors.origin}" })
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	private final UserService userService;
	private final ModelMapper modelMapper;

	@Autowired
	public ClienteController(ClienteService clienteService, UserService userService, ModelMapper modelMapper) {
		this.clienteService = clienteService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping(value = "/cliente/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ClienteVO findById(@PathVariable("id") Integer id) {
		ClienteVO clienteVO = clienteService.findById(id);
		clienteVO.add(linkTo(methodOn(ClienteController.class).findById(id)).withSelfRel());
		log.info("Find by id");
		return clienteVO;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping("/{userId}")
	@ResponseBody
	public List<ClienteVO> findClientesByUserId(@PathVariable("page") int page, @PathVariable("size") int size,
			@PathVariable("sortDir") String sortDir, @PathVariable("sort") String sort,
			@PathVariable("userId") Integer userId) {

		List<Cliente> posts = clienteService.findClientesByUserId(page, size, sortDir, sort, userId);
		return posts.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private ClienteVO convertToDto(Cliente cliente) {
		ClienteVO clienteVO = modelMapper.map(cliente, ClienteVO.class);
		return clienteVO;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping(value = "/lista-cliente/{usuarioLogado}", produces = { "application/hal+json", "application/xml",
			"application/x-yaml" })
	public ResponseEntity<Map<String, Object>> findAll(@RequestParam(required = false) String nome,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id,desc") String[] sort,
			@PathVariable(value = "usuarioLogado") final String usuarioLogado) {

		User user = userService.findByEmail(usuarioLogado);
		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] _sort = sortOrder.split(",");
					orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}

			List<ClienteVO> clienteVOs = new ArrayList<ClienteVO>();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			Page<ClienteVO> pageTuts;
			if (nome == null)
				// Passar o usuario logado
				pageTuts = clienteService.findAll(user, pagingSort);
			else
				pageTuts = clienteService.findByNomeLike(nome, user, pagingSort);

			
			clienteVOs = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("clientes", clienteVOs);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/{usuarioLogado}", 
				 produces = { "application/json", "application/xml", "application/x-yaml" }, 
				 consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClienteVO create(@RequestBody ClienteVO clienteVO,
			@PathVariable(value = "usuarioLogado") final String usuarioLogado) {
		User user = userService.findByEmail(usuarioLogado);
		boolean exists = clienteService.existsByCpf(clienteVO.getCpf());
		if (exists) {
			throw new ClienteCadastradoException(user.getCpf());
		}
		clienteVO.activate();
		clienteVO.setIdUsuario(user.getId());
		ClienteVO cliVO = clienteService.create(clienteVO);
		cliVO.setIdUsuario(user.getId());
		cliVO.add(linkTo(methodOn(ClienteController.class).findById(cliVO.getId())).withSelfRel());
		return cliVO;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@PutMapping(value = "/cliente/{usuarioLogado}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ClienteVO update(@RequestBody ClienteVO clienteVO,
			@PathVariable(value = "usuarioLogado") final String usuarioLogado) {
		User user = userService.findByEmail(usuarioLogado);
		ClienteVO cliVO = clienteService.update(user, clienteVO);
		cliVO.add(linkTo(methodOn(ClienteController.class).findById(cliVO.getId())).withSelfRel());
		return cliVO;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@DeleteMapping("/cliente/{usuarioLogado}/{id}")
	public ResponseEntity<?> delete(
			@PathVariable(value = "usuarioLogado") final String usuarioLogado, @PathVariable("id") Integer id) {
		User user = userService.findByEmail(usuarioLogado);
		clienteService.delete(user, id);
		return ResponseEntity.ok().build();
	}
}

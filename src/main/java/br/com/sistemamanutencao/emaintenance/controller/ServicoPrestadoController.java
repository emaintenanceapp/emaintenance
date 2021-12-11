package br.com.sistemamanutencao.emaintenance.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemamanutencao.emaintenance.exception.ClienteCadastradoException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.ServicoPrestadoVO;
import br.com.sistemamanutencao.emaintenance.service.ClienteService;
import br.com.sistemamanutencao.emaintenance.service.ServicoPrestadoService;
import br.com.sistemamanutencao.emaintenance.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

	private final ServicoPrestadoService servicoPrestadoService;
	private final ClienteService clienteService;
	private final UserService userService;
	private final ModelMapper modelMapper;

	@Autowired
	public ServicoPrestadoController(ServicoPrestadoService servicoPrestadoService, ClienteService clienteService, UserService userService, ModelMapper modelMapper) {
		this.servicoPrestadoService = servicoPrestadoService;
		this.clienteService = clienteService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

//    private final ClienteRepository clienteRepository;
//    private final UserRepository userRepository;
//	private final ServicoPrestadoRepository servicoPrestadoRepository;
	
	private ServicoPrestadoVO servicoPrestadoDTO = new ServicoPrestadoVO();
	
	private List<ServicoPrestadoVO> listaServicosPrestadosDTO = new ArrayList<ServicoPrestadoVO>();

//	@ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//    @PostMapping(value = "/{usuarioLogado}")
//	public ServicoPrestadoVO salvar(@RequestBody @Valid ServicoPrestadoVO dto, @PathVariable(value = "usuarioLogado") final String usuarioLogado) {
//		User user = userService.findByEmail(usuarioLogado);
//    	
//    	if (user.getActive()) {
//    		LocalDate data = dto.getData();
//    		Integer idCliente = dto.getCliente().getId();
//    		Cliente cliente = clienteService.findById(idCliente)
//    				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));
//
//    		ServicoPrestado servicoPrestado = new ServicoPrestado();
//    		servicoPrestado.setDescricao(dto.getDescricao());
//    		servicoPrestado.setData(data);
//    		servicoPrestado.setCliente(cliente);
//    		servicoPrestado.setPreco(dto.getPreco());
//    		servicoPrestado.setUser(user);
//    		log.info("Serviço Prestado salvo com sucesso! " + servicoPrestado.getId());
//    		return servicoPrestadoService.save(servicoPrestado);
//    	}
//    	return null;
//	}
	

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/{usuarioLogado}", 
				 produces = { "application/json", "application/xml", "application/x-yaml" }, 
				 consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ServicoPrestadoVO create(@RequestBody ServicoPrestadoVO servicoPrestadoVO,
			@PathVariable(value = "usuarioLogado") final String usuarioLogado) {
		User user = userService.findByEmail(usuarioLogado);
		boolean exists = servicoPrestadoService.existsByDescricao(servicoPrestadoVO.getDescricao());
		if (exists) {
			throw new ClienteCadastradoException(servicoPrestadoVO.getDescricao());
		}
		servicoPrestadoVO.activate();
		servicoPrestadoVO.setIdUsuario(user.getId());
		ServicoPrestadoVO cliVO = servicoPrestadoService.create(servicoPrestadoVO);
		cliVO.setIdUsuario(user.getId());
		cliVO.add(linkTo(methodOn(ClienteController.class).findById(cliVO.getId())).withSelfRel());
		return cliVO;
	}
	

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
	@GetMapping(value = "/lista-servicos-prestados/{usuarioLogado}", produces = { "application/hal+json", "application/xml",
			"application/x-yaml" })
	public ResponseEntity<Map<String, Object>> findAll(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes,
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

			List<ServicoPrestadoVO> servicoPrestadoVOs = new ArrayList<ServicoPrestadoVO>();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			Page<ServicoPrestadoVO> pageTuts;
			if (nome == null)
				// Passar o usuario logado
				pageTuts = servicoPrestadoService.findAll(user, pagingSort);
			else
				pageTuts = servicoPrestadoService.findByNomeAndMes("%" + nome + "%", mes, user, pagingSort);

			
			servicoPrestadoVOs = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("servicosPrestados", servicoPrestadoVOs);
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

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//	@GetMapping
//	public List<ServicoPrestado> pesquisar(
//			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
//			@RequestParam(value = "mes", required = false) Integer mes) {
//		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
//    	extractedServicoPrestado(listaServicosPrestados, servicoPrestadoDTO, dto);
//		return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
//	}

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//	@GetMapping("/pesquisar")
//	public List<ServicoPrestadoVO> pesquisarDTO(
//			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
//			@RequestParam(value = "mes", required = false) Integer mes) {
//		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoService.findByNomeClienteAndMes("%" + nome + "%", mes);
//		return listaServicosPrestadosDTO;
//	}
	
    // Consulta traz apenas os cadatrados pelo usuário corrente
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//	@GetMapping("/{userId}")
//	public List<ServicoPrestadoVO> findClientesByUser(@PathVariable Integer userId) {		
//		List<ServicoPrestado> listaServicosPrestados = servicoPrestadoService.findClientesByUserId(userId);		
//		return listaServicosPrestadosDTO;
//	}

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//	@GetMapping("/servico-prestado/{id}")
//	public ServicoPrestadoVO acharPorId(@PathVariable Integer id) {
//    	ServicoPrestado servicoPrestado = servicoPrestadoService.findById(id).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
//		return servicoPrestadoDTO;
//	}

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//	@DeleteMapping("{id}")
//	public void deletar(@PathVariable Integer id) {
//    	servicoPrestadoService.findById(id).map(servicoPrestado -> {
//			servicoPrestadoRepository.delete(servicoPrestado);
//			return Void.TYPE;
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
//	}

//	@ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//    @PutMapping(value = "/{usuarioLogado}/{id}")
//	public void atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoPrestado servicoPrestadoAtualizado, @PathVariable(value = "usuarioLogado") final String usuarioLogado ) {
//		User user = userService.findByEmail(usuarioLogado);
//		
//    	if (user.getActive()) {
//    		servicoPrestadoService.findById(id).map(servicoPrestado -> {
////    			servicoPrestado.setCliente(servicoPrestadoAtualizado.getCliente());
////    			servicoPrestado.setDescricao(servicoPrestadoAtualizado.getDescricao());
////    			servicoPrestado.setData(servicoPrestadoAtualizado.getData());
////    			servicoPrestado.setPreco(servicoPrestadoAtualizado.getPreco());
////    			servicoPrestado.setUser(user);
//    			log.info("Serviço Prestado atualizado com sucesso! " + servicoPrestado.getId());
//    			return servicoPrestadoRepository.save(servicoPrestado);
//    		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não encontrado"));
//    	}
//	}
	
}

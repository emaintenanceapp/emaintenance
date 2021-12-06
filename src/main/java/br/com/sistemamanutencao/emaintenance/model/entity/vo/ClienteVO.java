package br.com.sistemamanutencao.emaintenance.model.entity.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.PrePersist;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({ "id", "nome", "cpf", "idUsuario", "dataCadastro", "dataAtualizacao", "status" })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClienteVO extends RepresentationModel<ClienteVO> implements Serializable {

	private static final long serialVersionUID = 3080797503589849292L;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("idUsuario")
	private Integer idUsuario;

	@JsonProperty("dataCadastro")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@JsonProperty("dataAtualizacao")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataAtualizacao;

	@JsonProperty("status")
	private boolean status;

	public static ClienteVO create(Cliente cliente) {
		ClienteVO clienteVO = new ModelMapper().map(cliente, ClienteVO.class);
		return clienteVO;
	}
	
	@PrePersist
	public void prepersist() {
		setDataCadastro(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}
	
	public void activate() {
		this.status = true;
	}
	
	public void deactivate() {
		this.status = false;
	}
	
}

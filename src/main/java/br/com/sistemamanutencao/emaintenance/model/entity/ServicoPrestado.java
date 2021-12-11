package br.com.sistemamanutencao.emaintenance.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.ServicoPrestadoVO;
import lombok.Data;

@Entity
@Data
public class ServicoPrestado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column
	private String preco;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAtualizacao;

	@NotNull(message = "{campo.status.obrigatorio}")
	@Column
	private boolean status;
	
	public static ServicoPrestado create(ServicoPrestadoVO servicoPrestadoVO) {
		ServicoPrestado servicoPrestado = new ModelMapper().map(servicoPrestadoVO, ServicoPrestado.class);
		return servicoPrestado;
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

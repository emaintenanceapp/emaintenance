package br.com.sistemamanutencao.emaintenance.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sistemamanutencao.emaintenance.model.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

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

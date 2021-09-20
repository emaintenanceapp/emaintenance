package br.com.emaintenanceapplication.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fabricante implements Serializable {

	private static final long serialVersionUID = 5636751712112377869L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@Column
	@NotEmpty(message = "{campo.email.obrigatorio}")
	private String email;
	
	@Column
	@NotEmpty(message = "{campo.endereco.obrigatorio}")
	private String endereco;

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	@NotNull
	private LocalDate dataCadastro;
	
	@Column(name = "data_atualizacao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	@NotNull
	private LocalDate dataAtualizacao;
	
	@Column
	@NotNull
	private boolean status;

	@PrePersist
	public void prepersist() {
		setDataCadastro(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}

}

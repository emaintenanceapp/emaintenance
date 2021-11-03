package br.com.sistemamanutencao.emaintenance.model.entity;

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

	@NotEmpty(message = "{campo.fabricante.razao.social.obrigatorio}")
	@Column(nullable = false, length = 150)
	private String razaoSocial;

	@NotEmpty(message = "{campo.fabricante.nome.fantasia.obrigatorio}")
	@Column(nullable = false, length = 150)
	private String nomeFantasia;
	
	@Column(nullable = false, length = 150)
	private String nomeResponsavel;
	
	@Column(nullable = false, length = 150)
	private String emailResponsavel;
	
	@Column(nullable = false, length = 150)
	private String telefoneResponsavel;

	@NotEmpty(message = "{campo.fabricante.cnpj.obrigatorio}")
	@Column
	private String cnpj;
	
	@Column
	private String email;
	
	@Column
	private String telefone;
	
	@NotEmpty(message = "{campo.fabricante.cep.obrigatorio}")
	@Column
	private String cep;
	
	@NotEmpty(message = "{campo.fabricante.endereco.obrigatorio}")
	@Column
	private String endereco;
	
	@NotEmpty(message = "{campo.fabricante.numero.obrigatorio}")
	@Column
	private String numero;
	
	@Column
	private String complemento;
	
	@NotEmpty(message = "{campo.fabricante.bairro.obrigatorio}")
	@Column
	private String bairro;
	
	@NotEmpty(message = "{campo.fabricante.cidade.obrigatorio}")
	@Column
	private String cidade;
	
	@NotEmpty(message = "{campo.fabricante.uf.obrigatorio}")
	@Column
	private String uf;
	
	@NotEmpty(message = "{campo.fabricante.estado.obrigatorio}")
	@Column
	private String estado;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_cadastro", updatable = false)
	private LocalDate dataCadastro;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_atualizacao", updatable = true)
	private LocalDate dataAtualizacao;
	
	@NotNull
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

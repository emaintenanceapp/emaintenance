package br.com.emaintenanceapplication.model.entity;

import java.io.Serializable;
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
public class Manutencao implements Serializable{
	
	private static final long serialVersionUID = 3347782995422585996L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotNull(message = "{campo.titulo.manutencao.obrigatorio}")
	private String tituloManutencao;
	
	@Column(nullable = false, length = 2000)
	@NotNull(message = "{campo.descricao.manutencao.obrigatorio}")
	private String descricaoManutencao;
	
	@Column(nullable = false, length = 150)
	@NotNull
	private String nomeOperador;
	
	@Column(nullable = false, length = 150)
	@NotNull
	private String nomeManutenedor;
	
	@ManyToOne
	@JoinColumn(name = "id_equipamento")
	@NotNull(message = "{campo.equipamento.obrigatorio}")
	private Equipamento equipamento;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_manutencao")
	@NotNull(message = "{campo.tipo.manutencao.obrigatorio}")
	private TipoManutencao tipoManutencao;
	
	@ManyToOne
	@JoinColumn(name = "id_criticidade")
	@NotNull(message = "{campo.criticidade.obrigatorio}")
	private Criticidade criticidade;

	@Column(name = "data_manutencao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataManutencao;
	
	@Column(name = "data_ultima_manutencao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataUltimaManutencao;
	
	@Column(name = "data_proxima_manutencao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	@NotNull
	private LocalDate dataProximaManutencao;
	
	@Column(name = "dias_finalizacao", updatable = true)
	private int diasFinalizacao;

	@Column(name = "dias_totais", updatable = true)
	private int diasTotais;
	
	@Column
	@NotNull
	private boolean pauseManutencao;
	
	@Column(name = "data_retorno_pause", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataRetornoPause;
	
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	@NotNull
	private LocalDate dataCadastro;
	
	@Column(name = "data_atualizacao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	@NotNull
	private LocalDate dataAtualizacao;
	
	@Column
	@NotNull(message = "{campo.status.obrigatorio}")
	private boolean status;

	@PrePersist
	public void prepersist() {
		setDataCadastro(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}

}

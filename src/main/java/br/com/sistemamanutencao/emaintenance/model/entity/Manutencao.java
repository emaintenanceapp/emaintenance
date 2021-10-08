package br.com.sistemamanutencao.emaintenance.model.entity;

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
	
	@NotNull(message = "{campo.titulo.manutencao.obrigatorio}")
	@Column(nullable = false, length = 150)
	private String tituloManutencao;
	
	@NotNull(message = "{campo.descricao.manutencao.obrigatorio}")
	@Column(nullable = false, length = 2000)
	private String descricaoManutencao;
	
	@NotNull
	@Column(nullable = false, length = 150)
	private String nomeOperador;
	
	@NotNull
	@Column(nullable = false, length = 150)
	private String nomeManutenedor;
	
	@NotNull(message = "{campo.equipamento.obrigatorio}")
	@ManyToOne
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;
	
	@NotNull(message = "{campo.tipo.manutencao.obrigatorio}")
	@ManyToOne
	@JoinColumn(name = "id_tipo_manutencao")
	private TipoManutencao tipoManutencao;
	
	@NotNull(message = "{campo.criticidade.obrigatorio}")
	@ManyToOne
	@JoinColumn(name = "id_criticidade")
	private Criticidade criticidade;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataManutencao;
	
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataUltimaManutencao;
	
	@NotNull
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataProximaManutencao;
	
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
	private int diasFinalizacao;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
	private int diasTotais;
	
	@Column
	@NotNull
	private boolean pauseManutencao;
	
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataRetornoPause;
	
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

}

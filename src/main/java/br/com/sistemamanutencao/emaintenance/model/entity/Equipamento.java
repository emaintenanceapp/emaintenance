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
public class Equipamento implements Serializable{

	private static final long serialVersionUID = 8593887925410276544L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Column(nullable = false, length = 200)
	private String nome;
	
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	@Column
	private String descricao;
	
	@NotNull(message = "{campo.numero.patrimonio.obrigatorio}")
	@Column(nullable = false, length = 20)
	private String numeroPatrimonio;
	
	@NotNull(message = "{campo.numero.tag.obrigatorio}")
	@Column(nullable = false, length = 50)
	private String numeroTag;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
	private Fabricante idFabricante;

    @ManyToOne
    @JoinColumn(name = "id_tipo_equipamento")
	private TipoEquipamento idTipoEquipamento;
	
    @NotNull(message = "{campo.operacional.obrigatorio}")
	@Column
	private boolean operacional;
	
    @NotNull(message = "{campo.defeito.obrigatorio}")
	@Column
	private boolean defeito;
	
    @NotNull(message = "{campo.localizacao.obrigatorio}")
	@Column
	private String localizacao;

    @JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_cadastro", updatable = false)
	private LocalDate dataCadastro;
	
    @JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_atualizacao", updatable = true)
	private LocalDate dataAtualizacao;
	
    @NotNull(message = "{campo.status.obrigatorio}")
	@Column
	private boolean status;

	@PrePersist
	public void prepersist(){
		setDataCadastro(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}

}


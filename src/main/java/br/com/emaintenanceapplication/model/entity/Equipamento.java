package br.com.emaintenanceapplication.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
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

	@Column(nullable = false, length = 200)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Column
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@Column(nullable = false, length = 20)
	@NotNull(message = "{campo.numero.patrimonio.obrigatorio}")
	private String numeroPatrimonio;
	
	@Column(nullable = false, length = 50)
	@NotNull(message = "{campo.numero.tag.obrigatorio}")
	private String numeroTag;

	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	@NotNull(message = "{campo.fabricante.obrigatorio}")
	private Fabricante idFabricante;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_equipamento")
	@NotNull(message = "{campo.tipo.equipamento.obrigatorio}")
	private TipoEquipamento idTipoEquipamento;
	
	@Column
	@NotNull(message = "{campo.operacional.obrigatorio}")
	private boolean operacional;
	
	@Column
	@NotNull(message = "{campo.defeito.obrigatorio}")
	private boolean defeito;
	
	@Column
	@NotNull(message = "{campo.localizacao.obrigatorio}")
	private String localizacao;

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;
	
	@Column(name = "data_atualizacao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataAtualizacao;
	
	@Column
	@NotNull(message = "{campo.status.obrigatorio}")
	private boolean status;

	@PrePersist
	public void prepersist(){
		setDataCadastro(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}

}


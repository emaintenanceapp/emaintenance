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

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.EquipamentoVO;
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
    
    //Avalia????o das condi????es gerais do equipamento
    private String avaliacaoIntegridade;

    //Quanto o equipamento pode operar em porcentagem, ex: 10%, 50%, 100%
    private String porcentagemOperacional;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_cadastro", updatable = false)
	private LocalDate dataCadastro;
	
    @JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_atualizacao", updatable = true)
	private LocalDate dataAtualizacao;
	
    @NotNull(message = "{campo.status.obrigatorio}")
	@Column
	private boolean status;
    
	public static Equipamento create(EquipamentoVO equipamentoVO) {
		return new ModelMapper().map(equipamentoVO, Equipamento.class);
	}

	@PrePersist
	public void prepersist(){
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


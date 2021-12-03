package br.com.sistemamanutencao.emaintenance.model.entity.vo;

import java.io.Serializable;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.sistemamanutencao.emaintenance.model.entity.Equipamento;
import br.com.sistemamanutencao.emaintenance.model.entity.Fabricante;
import br.com.sistemamanutencao.emaintenance.model.entity.TipoEquipamento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","descricao","numeroPatrimonio","numeroTag","idFabricante","idTipoEquipamento","operacional","defeito","localizacao","dataCadastro","dataAtualizacao","status"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EquipamentoVO extends RepresentationModel<EquipamentoVO> implements Serializable {

	private static final long serialVersionUID = -2322009020283228664L;

	@JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("numeroPatrimonio")
    private String numeroPatrimonio;

    @JsonProperty("numeroTag")
    private String numeroTag;
    
    @JsonProperty("idTipoEquipamento")
    private TipoEquipamento idTipoEquipamento;

    @JsonProperty("idFabricante")
    private Fabricante idFabricante;

    @JsonProperty("operacional")
    private boolean operacional;

    @JsonProperty("defeito")
    private boolean defeito;

    @JsonProperty("localizacao")
    private String localizacao;
    
    //Avaliação das condições gerais do equipamento
    @JsonProperty("avaliacaoIntegridade")
    private String avaliacaoIntegridade;

    //Quanto o equipamento pode operar em porcentagem, ex: 10%, 50%, 100%
    @JsonProperty("porcentagemOperacional")
    private String porcentagemOperacional;
    
	@JsonProperty("idUsuario")
    private UserVO user;

    @JsonProperty("dataCadastro")
    private LocalDate dataCadastro;

    @JsonProperty("dataAtualizacao")
    private LocalDate dataAtualizacao;

    @JsonProperty("status")
    private boolean status;

    public static EquipamentoVO create(Equipamento equipamento) {
        return new ModelMapper().map(equipamento, EquipamentoVO.class);
    }
}


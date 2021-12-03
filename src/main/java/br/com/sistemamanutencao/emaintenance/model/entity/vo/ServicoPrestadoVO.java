package br.com.sistemamanutencao.emaintenance.model.entity.vo;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@JsonPropertyOrder({"id","descricao","preco","data","cliente","user","dataCadastro","dataAtualizacao","status"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServicoPrestadoVO extends RepresentationModel<ServicoPrestadoVO> implements Serializable {

	private static final long serialVersionUID = -2322009020283228664L;

	@JsonProperty("id")
    private Integer id;

	@JsonProperty("descricao")
    private String descricao;

	@JsonProperty("preco")
    private String preco;

	@JsonProperty("data")
    private LocalDate data;

	@JsonProperty("cliente")
    private ClienteVO cliente;

	@JsonProperty("user")
    private UserVO user;
    
	@JsonProperty("dataCadastro")
	private LocalDate dataCadastro;
	
	@JsonProperty("dataAtualizacao")
	private LocalDate dataAtualizacao;	
	
	@JsonProperty("status")
	private boolean status;
}

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

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sistemamanutencao.emaintenance.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoEquipamento implements Serializable {

	private static final long serialVersionUID = -6563547183533561065L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String nome;
	
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

	@JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_cadastro", updatable = false)
	private LocalDate dataCadastro;
	
	@JsonFormat(pattern = "dd/MM/yyy")
	@Column(name = "data_atualizacao", updatable = true)
	private LocalDate dataAtualizacao;
	
	@Column
	private boolean status;

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

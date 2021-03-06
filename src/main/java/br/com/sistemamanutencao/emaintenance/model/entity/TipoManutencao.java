package br.com.sistemamanutencao.emaintenance.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoManutencao implements Serializable {

	private static final long serialVersionUID = -5687351488291406826L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column
	private boolean status;    
    
	public void activate() {
		this.status = true;
	}
	
	public void deactivate() {
		this.status = false;
	}

}

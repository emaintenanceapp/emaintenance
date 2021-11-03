package br.com.sistemamanutencao.emaintenance.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sistemamanutencao.emaintenance.model.User;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;
	
	@Column(name = "data_atualizacao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataAtualizacao;
		
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

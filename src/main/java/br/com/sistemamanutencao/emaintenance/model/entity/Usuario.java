package br.com.sistemamanutencao.emaintenance.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements Serializable{

	private static final long serialVersionUID = 4643321953900850523L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "{campo.usuario.username.obrigatorio}")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "{campo.usuario.password.obrigatorio}")
    private String password;
    
    @Transient
    private String confirmaSenha;
    
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.usuario.nome.usuario.obrigatorio}")
    private String nomeUsuario;
    
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.usuario.email.obrigatorio}")
    private String email;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.usuario.cpf.invalido}")
    private String cpf;

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

}

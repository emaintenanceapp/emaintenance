package br.com.sistemamanutencao.emaintenance.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sistemamanutencao.emaintenance.model.entity.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer id;

    @Column
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Column
    @NotNull(message = "Password é obrigatório")
    private String password;

    @Column
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    
    @Column
    private String lastName;

    @Column(length = 11, unique = true)
    @CPF(message = "{campo.usuario.cpf.invalido}")
    private String cpf;

    @Column(nullable = false)
    private Boolean active;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataCadastro;
	
	@Column(name = "data_atualizacao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyy")
	private LocalDate dataAtualizacao;
	
	public static User create(UserVO userVO) {
		return new ModelMapper().map(userVO, User.class);
	}
	
	@PrePersist
	public void prepersist() {
		setDataCadastro(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}
    
    public void activate() {
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;
	}
}

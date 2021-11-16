package br.com.sistemamanutencao.emaintenance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Integer id;

	private String email;

	private String nomeUser;
}

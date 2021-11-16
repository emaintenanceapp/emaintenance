package br.com.sistemamanutencao.emaintenance.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private Integer id;
    private String email;
    private String name;
    private Boolean active;
}

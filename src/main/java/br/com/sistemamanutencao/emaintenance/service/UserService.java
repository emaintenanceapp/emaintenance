package br.com.sistemamanutencao.emaintenance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.dto.UserDTO;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
	
	public UserDTO findByEmailDTO(String email) {
		User user = repository.findByEmail(email);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setNomeUser(user.getName());
		
		return userDTO;
	}
	
}

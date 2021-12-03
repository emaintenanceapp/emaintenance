package br.com.sistemamanutencao.emaintenance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.UserVO;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
	
	public UserVO findByEmailDTO(String email) {
		User user = repository.findByEmail(email);
		UserVO userDTO = new UserVO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setNomeUser(user.getName());
		
		return userDTO;
	}

	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
}

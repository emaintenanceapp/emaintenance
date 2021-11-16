package br.com.sistemamanutencao.emaintenance.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    UserRepository userRepository;
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
      
//        User user = userRepository.findByEmailIdIgnoreCase(username)
//                  .orElseThrow(() -> 
//                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
//        );
		
		User user = userRepository.findByEmail(username);
 
        return UserPrincipal.build(user);
    }
    
}

package br.com.sistemamanutencao.emaintenance.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.exception.SpringEmaintenanceException;
import br.com.sistemamanutencao.emaintenance.exception.UserCadastradoException;
import br.com.sistemamanutencao.emaintenance.model.ConfirmationToken;
import br.com.sistemamanutencao.emaintenance.model.Role;
import br.com.sistemamanutencao.emaintenance.model.RoleName;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.entity.NotificationEmail;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.SignUpForm;
import br.com.sistemamanutencao.emaintenance.repository.ConfirmationTokenRepository;
import br.com.sistemamanutencao.emaintenance.repository.RoleRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService {
	 
    @Autowired
    UserRepository userRepository;
    
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	EmailSenderService mailService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	public void register(SignUpForm signUpRequest) {
		User user = new User();
		user.setName(signUpRequest.getName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
				roles.add(adminRole);

				break;
			case "therapist":
				Role therapistRole = roleRepository.findByName(RoleName.ROLE_THERAPIST)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
				roles.add(therapistRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		user.deactivate();

        userRepository.save(user);

		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenRepository.save(confirmationToken);

		String token = generateVerificationToken(user);
		
		mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Obrigado por se cadastrar, "
						+ "por favor clique no botão abaixo para ativar sua conta:<br/><br/><br/><br/> "
						+ "<a href=\"https://emaintenanceapplication.herokuapp.com/api/auth/confirm-account/\">Ativar Conta</a>" 
						+ " " + token));
	}
    
    
	public void verifyAccount(String token) {
		Optional<ConfirmationToken> verificationToken = confirmationTokenRepository.findByConfirmationToken(token);
		fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringEmaintenanceException("Invalid Token")));
	}
	
	private void fetchUserAndEnable(ConfirmationToken confirmationToken) {
		String email = confirmationToken.getUser().getEmail();
		boolean exists = userRepository.existsByEmail(email);
		if(!exists){
			log.info("Usuário não existe!");
            throw new UserCadastradoException(email, HttpStatus.CONFLICT);
		}

		User user = userRepository.findByEmail(email);
		user.activate();
		userRepository.save(user);
		log.info("Usuário verificado com sucesso!");
	}
	

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		ConfirmationToken verificationToken = new ConfirmationToken();
		verificationToken.setConfirmationToken(token);
		verificationToken.setUser(user);

		confirmationTokenRepository.save(verificationToken);
		return token;
	}
}

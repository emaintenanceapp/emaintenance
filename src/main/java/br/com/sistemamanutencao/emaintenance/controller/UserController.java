package br.com.sistemamanutencao.emaintenance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemamanutencao.emaintenance.dto.LogOutRequest;
import br.com.sistemamanutencao.emaintenance.dto.UserDTO;
import br.com.sistemamanutencao.emaintenance.event.OnUserLogoutSuccessEvent;
import br.com.sistemamanutencao.emaintenance.exception.ResourceNotFoundException;
import br.com.sistemamanutencao.emaintenance.exception.UserLogoutException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.UserDevice;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import br.com.sistemamanutencao.emaintenance.response.ApiResponse;
import br.com.sistemamanutencao.emaintenance.response.UserProfile;
import br.com.sistemamanutencao.emaintenance.service.CurrentUser;
import br.com.sistemamanutencao.emaintenance.service.RefreshTokenService;
import br.com.sistemamanutencao.emaintenance.service.UserDeviceService;
import br.com.sistemamanutencao.emaintenance.service.UserPrincipal;
import br.com.sistemamanutencao.emaintenance.service.UserService;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
        
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private UserDeviceService userDeviceService;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @GetMapping()
    public List<User> obterTodos(){
        return userRepository.findAll();
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
////    @PostMapping("/{usuarioLogado}")
////    public User getCurrentUser(@Valid @RequestBody String usuarioLogado) {
//    @GetMapping("/me/{usuarioLogado}")
//    public User getCurrentUser(@PathVariable(value = "usuarioLogado") final String usuarioLogado) {
//		User user = userRepository.findByEmail(usuarioLogado);
//        return user;
//    }
    
    // Consulta traz apenas os cadatrados pelo usuário corrente
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
//    @GetMapping(value = "/me/{usuarioLogado}", produces="application/json")
//    public User getCurrentUser(@PathVariable String usuarioLogado) {		
//    	User user = userRepository.findByEmail(usuarioLogado);		
//    	return user;
//    }

    // Consulta traz apenas os cadatrados pelo usuário corrente
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS', 'ROLE_ANON')")
    @GetMapping(value = "/me/{usuarioLogado}", produces="application/json")
    public UserDTO getCurrentUserDTO(@PathVariable String usuarioLogado) {		
    	UserDTO user = userService.findByEmailDTO(usuarioLogado);		
    	return user;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @GetMapping("/list")
    public List<UserProfile> getUserProfile(@RequestParam(value = "email", required = false) String email) {
    	List<UserProfile> userProfiles = new ArrayList<>();
    	if (email != null) {
//    		User user = userRepository.findByEmailIdIgnoreCase(email.get())
//                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email.get()));
    		
    		User user = userRepository.findByEmail(email);
    		
    		UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getName(),  user.getActive());
    		userProfiles.add(userProfile);
    	} else {
    		List<User> users = userRepository.findAll();
    		for (User u: users) {
    			UserProfile userProfile = new UserProfile(u.getId(), u.getEmail(), u.getName(),  u.getActive());
    			userProfiles.add(userProfile);
    		}
    	}
        return userProfiles;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @GetMapping("/byID/{id}")
    public UserProfile getUserProfileById(@PathVariable(value = "id") Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getName(), user.getActive());

        return userProfile;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @PutMapping("/byID/{id}/deactivate")
    public ResponseEntity<ApiResponse> deactivateUserById(@PathVariable(value = "id") Integer id) {
    	User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.deactivate();
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User deactivated successfully!"));  
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @PutMapping("/byID/{id}/activate")
    public ResponseEntity<ApiResponse> activateUserById(@PathVariable(value = "id") Integer id) {
       User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.activate();
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User activated successfully!"));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @DeleteMapping("/byID/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id") Integer id) {
    	User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully!"));
    }
        
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    @PutMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser(@CurrentUser UserPrincipal currentUser,
    		@Valid @RequestBody LogOutRequest logOutRequest) {
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
        UserDevice userDevice = userDeviceService.findByUserId(currentUser.getId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), 
                		"ID de dispositivo inválido fornecido. Nenhum dispositivo correspondente encontrado para o usuário fornecido."));
        refreshTokenService.deleteById(userDevice.getRefreshToken().getId());
        
        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(currentUser.getEmail(), logOutRequest.getToken(), logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseEntity.ok(new ApiResponse(true, "O usuário saiu do sistema com sucesso!"));
    }

}

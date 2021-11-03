package br.com.sistemamanutencao.emaintenance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.com.sistemamanutencao.emaintenance.event.OnUserLogoutSuccessEvent;
import br.com.sistemamanutencao.emaintenance.exception.ResourceNotFoundException;
import br.com.sistemamanutencao.emaintenance.exception.UserLogoutException;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.UserDevice;
import br.com.sistemamanutencao.emaintenance.model.entity.Cliente;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import br.com.sistemamanutencao.emaintenance.response.ApiResponse;
import br.com.sistemamanutencao.emaintenance.response.UserProfile;
import br.com.sistemamanutencao.emaintenance.service.CurrentUser;
import br.com.sistemamanutencao.emaintenance.service.RefreshTokenService;
import br.com.sistemamanutencao.emaintenance.service.UserDeviceService;
import br.com.sistemamanutencao.emaintenance.service.UserPrincipal;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
        
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private UserDeviceService userDeviceService;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_ASSISTANT_MANAGER', 'ROLE_STAFF_MEMBER', 'ROLE_USER','ROLE_ANONYMOUS')")
    public List<User> obterTodos(){
        return userRepository.findAll();
    }

    @GetMapping("/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfile> getUserProfile(@RequestParam(value = "email", required = false) Optional<String> email) {
    	List<UserProfile> userProfiles = new ArrayList<>();
    	if (email.isPresent()) {
    		User user = userRepository.findByEmail(email.get())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email.get()));
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

    @GetMapping("/byID/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfile getUserProfileById(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getName(), user.getActive());

        return userProfile;
    }

    @PutMapping("/byID/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deactivateUserById(@PathVariable(value = "id") Long id) {
    	User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.deactivate();
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User deactivated successfully!"));  
    }

    @PutMapping("/byID/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> activateUserById(@PathVariable(value = "id") Long id) {
       User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.activate();
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User activated successfully!"));
    }

    @DeleteMapping("/byID/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id") Long id) {
    	User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully!"));
    }
        
    @PutMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser(@CurrentUser UserPrincipal currentUser,
    		@Valid @RequestBody LogOutRequest logOutRequest) {
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
        UserDevice userDevice = userDeviceService.findByUserId(currentUser.getId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "Invalid device Id supplied. No matching device found for the given user "));
        refreshTokenService.deleteById(userDevice.getRefreshToken().getId());
        
        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(currentUser.getEmail(), logOutRequest.getToken(), logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseEntity.ok(new ApiResponse(true, "User has successfully logged out from the system!"));
    }

}

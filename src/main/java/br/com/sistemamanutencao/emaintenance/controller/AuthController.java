// NSJC&NSMS
package br.com.sistemamanutencao.emaintenance.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemamanutencao.emaintenance.exception.TokenRefreshException;
import br.com.sistemamanutencao.emaintenance.model.ConfirmationToken;
import br.com.sistemamanutencao.emaintenance.model.RefreshToken;
import br.com.sistemamanutencao.emaintenance.model.User;
import br.com.sistemamanutencao.emaintenance.model.UserDevice;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.DeviceInfo;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.LoginForm;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.SignUpForm;
import br.com.sistemamanutencao.emaintenance.model.entity.vo.TokenRefreshRequest;
import br.com.sistemamanutencao.emaintenance.repository.ConfirmationTokenRepository;
import br.com.sistemamanutencao.emaintenance.repository.RoleRepository;
import br.com.sistemamanutencao.emaintenance.repository.UserRepository;
import br.com.sistemamanutencao.emaintenance.response.ApiResponse;
import br.com.sistemamanutencao.emaintenance.response.JwtResponse;
import br.com.sistemamanutencao.emaintenance.response.UserIdentityAvailability;
import br.com.sistemamanutencao.emaintenance.security.JwtProvider;
import br.com.sistemamanutencao.emaintenance.service.AuthService;
import br.com.sistemamanutencao.emaintenance.service.RefreshTokenService;
import br.com.sistemamanutencao.emaintenance.service.UserDeviceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = { "${app.security.cors.origin}" })
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private UserDeviceService userDeviceService;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
    private final AuthService authService;

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest,
			final HttpServletRequest request) {
//    	User user = userRepository.findByEmailIdIgnoreCase(loginRequest.getEmail())
//    			.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));
//    	
		String email  = loginRequest.getEmail();
		
		User user = userRepository.findByEmail(email);

		if (user.getActive()) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtProvider.generateJwtToken(authentication);
			userDeviceService.findByUserId(user.getId()).map(UserDevice::getRefreshToken).map(RefreshToken::getId)
					.ifPresent(refreshTokenService::deleteById);
			String clienteOS = getClientOS(request);
			String deviceType = getClientBrowser(request);
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfo.setDeviceId(clienteOS);
			deviceInfo.setDeviceType(deviceType);
			printClientInfo(request);
			UserDevice userDevice = userDeviceService.createUserDevice(deviceInfo);
			RefreshToken refreshToken = refreshTokenService.createRefreshToken();
			userDevice.setUser(user);
			userDevice.setRefreshToken(refreshToken);
			refreshToken.setUserDevice(userDevice);
			refreshToken = refreshTokenService.save(refreshToken);
			log.info("Usuário logado com sucesso!");
			return ResponseEntity
					.ok(new JwtResponse(jwtToken, refreshToken.getToken(), jwtProvider.getExpiryDuration()));
		}
		return ResponseEntity.badRequest().body(new ApiResponse(false, "User has been deactivated/locked !!"));
	}

//	@PostMapping("/register")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return new ResponseEntity<String>("Usuário já resgistrado com esse email!", HttpStatus.BAD_REQUEST);
//		}
////      Creating user's account
//		User user = new User();
//		user.setName(signUpRequest.getName());
//		user.setEmail(signUpRequest.getEmail());
//		user.setPassword(encoder.encode(signUpRequest.getPassword()));
//
//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();
//
//		strRoles.forEach(role -> {
//			switch (role) {
//			case "admin":
//				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
//				roles.add(adminRole);
//
//				break;
//			case "therapist":
//				Role therapistRole = roleRepository.findByName(RoleName.ROLE_THERAPIST)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
//				roles.add(therapistRole);
//
//				break;
//			default:
//				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
//				roles.add(userRole);
//			}
//		});
//
//		user.setRoles(roles);
//		user.deactivate();
//		User result = new User();
//        User result = userRepository.save(user);
//
//		ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//		confirmationTokenRepository.save(confirmationToken);
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo(user.getEmail());
//		mailMessage.setSubject("Complete Registration!");
//		mailMessage.setFrom("alvarofederal@gmail.com");
//		mailMessage.setText("To confirm your account, please click here : "
//				+ "http://localhost:8082/confirm-account?token=" + confirmationToken.getConfirmationToken());
//		String token = generateVerificationToken(user);
//		
//		authService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
//				"Thank you for signing up to Spring Reddit, "
//						+ "please click on the below url to activate your account : "
//						+ "http://localhost:8080/api/auth/confirm-account/" + token));
//
//		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
//				.buildAndExpand(result.getId()).toUri();
//		return ResponseEntity.created(location).body(new ApiResponse(true, "Usuário registrado com sucesso!"));
//	}

    @PostMapping("/register")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			log.info("Usuário não encontrado!");
			return new ResponseEntity<String>("Usuário já cadastrado com esse email!", HttpStatus.CONFLICT);
		}
		log.info("Usuário cadastrado com sucesso!");
        authService.register(signUpRequest);
        return new ResponseEntity<>("Usuário cadastrado com sucesso!", OK);
    }
    
//	@RequestMapping(value = "/confirm-account/{token}", method = { RequestMethod.GET, RequestMethod.POST })
//	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
//		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//
//		if (token != null) {
//			User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
//			user.activate();
//			User result = userRepository.save(user);
//
//			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
//					.buildAndExpand(result.getId()).toUri();
//	        return new ResponseEntity<>("Account Activated Successfully", OK);
//		}
//		return ResponseEntity.badRequest().body(new ApiResponse(false, "Usuário se encontra desativado/bloqueado!!"));
//	}
	
    @GetMapping("confirm-account/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Conta ativada com sucesso!", OK);
    }

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshJwtToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {

		String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
		Optional<String> token = Optional.of(refreshTokenService.findByToken(requestRefreshToken).map(refreshToken -> {
			refreshTokenService.verifyExpiration(refreshToken);
			userDeviceService.verifyRefreshAvailability(refreshToken);
			refreshTokenService.increaseCount(refreshToken);
			return refreshToken;
		}).map(RefreshToken::getUserDevice).map(UserDevice::getUser).map(u -> jwtProvider.generateTokenFromUser(u))
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
						"Token de atualizacao inexistente. Por favor faca login novamente.")));
		return ResponseEntity.ok().body(
				new JwtResponse(token.get(), tokenRefreshRequest.getRefreshToken(), jwtProvider.getExpiryDuration()));
	}

	@GetMapping("/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		ConfirmationToken verificationToken = new ConfirmationToken();
		verificationToken.setConfirmationToken(token);
		verificationToken.setUser(user);
		confirmationTokenRepository.save(verificationToken);
		return token;
	}
	
	public String printClientInfo(HttpServletRequest request) {
		final String referer = getReferer(request);
		final String fullURL = getFullURL(request);
		final String clientIpAddr = getClientIpAddr(request);
		final String clientOS = getClientOS(request);
		final String clientBrowser = getClientBrowser(request);
		final String userAgent = getUserAgent(request);

		log.info("\n" + "User Agent \t" + userAgent + "\n" + "Operating System\t" + clientOS + "\n" + "Browser Name\t"
				+ clientBrowser + "\n" + "IP Address\t" + clientIpAddr + "\n" + "Full URL\t" + fullURL + "\n"
				+ "Referrer\t" + referer);
		return clientBrowser;
	}

	public String getReferer(HttpServletRequest request) {
		final String referer = request.getHeader("referer");
		return referer;
	}

	public String getFullURL(HttpServletRequest request) {
		final StringBuffer requestURL = request.getRequestURL();
		final String queryString = request.getQueryString();

		final String result = queryString == null ? requestURL.toString()
				: requestURL.append('?').append(queryString).toString();

		return result;
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientOS(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");

		// =================OS=======================
		final String lowerCaseBrowser = browserDetails.toLowerCase();
		if (lowerCaseBrowser.contains("windows")) {
			return "Windows";
		} else if (lowerCaseBrowser.contains("mac")) {
			return "Mac";
		} else if (lowerCaseBrowser.contains("x11")) {
			return "Unix";
		} else if (lowerCaseBrowser.contains("android")) {
			return "Android";
		} else if (lowerCaseBrowser.contains("iphone")) {
			return "IPhone";
		} else {
			return "UnKnown, More-Info: " + browserDetails;
		}
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientBrowser(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");
		final String user = browserDetails.toLowerCase();

		String browser = "";

		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("edg")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Edg")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("chrome")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + browserDetails;
		}

		return browser;
	}

	public String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
}

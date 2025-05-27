package me.dionclei.dchat.controllers;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.dionclei.dchat.dto.AuthRequest;
import me.dionclei.dchat.dto.LoginResponse;
import me.dionclei.dchat.services.interfaces.TokenService;
import me.dionclei.dchat.services.interfaces.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserService userService;
	private AuthenticationManager manager;
	private TokenService tokenService;
	
	public AuthController(UserService userService, AuthenticationManager manager, TokenService tokenService) {
		this.userService = userService;
		this.manager = manager;
		this.tokenService = tokenService;
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody @Valid AuthRequest request) throws MethodArgumentNotValidException {
		var user = userService.findByName(request.name());
		
        BindingResult bindingResult = new BeanPropertyBindingResult(request.name(), "name");
        bindingResult.addError(new FieldError("name", "name", request.name(), false, null, null, "name must be unique"));

        MethodParameter methodParameter = new MethodParameter(this.getClass().getDeclaredMethods()[0], -1);

		if (user.isPresent()) throw new MethodArgumentNotValidException(methodParameter, bindingResult);
		
		userService.createUser(request.name(), request.password());
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
		UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(request.name(), request.password());
		var auth = manager.authenticate(username);
		var user = userService.findByName(auth.getName());
		if (user.isPresent()) {
			var token = tokenService.generateToken(user.get());
			return ResponseEntity.ok().body(new LoginResponse(token));
		}
		return ResponseEntity.notFound().build();
	}
}

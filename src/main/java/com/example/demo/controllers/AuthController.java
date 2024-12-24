package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.entity.AppUser;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		AppUser user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));
		String jwt = jwtUtils.generateJwtToken(user);

		return ResponseEntity.ok(jwt);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody SignUpDTO signUpDto) throws Exception {

		// add check for email exists in DB
		Optional<AppUser> existUser = userRepository.findByUsername(signUpDto.getUsername());
		if (existUser.isPresent()) {
			
			return new ResponseEntity<>("failed user already exist", HttpStatus.BAD_REQUEST);
		}

		AppUser user = null;
		// create user object
			
			user = new AppUser();
			user.setEmail(signUpDto.getEmail());
			user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
			user.setUsername(signUpDto.getUsername());
			
			user = userRepository.saveAndFlush(user);
			final String token = jwtUtils.generateJwtToken(user);
	
		return ResponseEntity.ok(token);

	}
	
	@PostMapping("/authenticated")
	public ResponseEntity<?> authenticateUser() {
		
		return ResponseEntity.ok("authenticated User");

	}
}

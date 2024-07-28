package com.grocery.authservice.service;

import com.grocery.authservice.entity.User;
import com.grocery.authservice.entity.UserType;
import com.grocery.authservice.repository.UserRepository;
import com.grocery.authservice.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtService jwtService;

	public String saveUser(User user) {
//        UserType userType = userTypeRepository.findById(user.getUserType().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user type ID"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserType(user.getUserType());
		userRepository.save(user);
		return "User successfully added";
	}

	public String generateToken(User user) {
		return jwtService.generateToken(user);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
